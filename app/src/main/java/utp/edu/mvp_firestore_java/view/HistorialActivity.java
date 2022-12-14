package utp.edu.mvp_firestore_java.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.model.Historial;
import utp.edu.mvp_firestore_java.model.Usuario;

public class HistorialActivity extends AppCompatActivity {
    AutoCompleteTextView acListaIntegrantes;
    FirebaseFirestore firestore;
    TextInputLayout tlIntegrantes;
    BarChart chartActividadA;
    BarChart chartActividadB;
    String idSesion;
    int cantActA,cantActB;
    ArrayList<Usuario> usuarios;
    Toolbar tbHistorial;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        chartActividadA = findViewById(R.id.chartHistorialA);
        chartActividadB = findViewById(R.id.chartHistorialB);
        acListaIntegrantes = findViewById(R.id.acListaIntegrantes);
        tbHistorial  = findViewById(R.id.tbHistorial);
        tlIntegrantes =findViewById(R.id.tlIntegrantes);
        setSupportActionBar(tbHistorial);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        firestore = FirebaseFirestore.getInstance();
        idSesion = getIntent().getStringExtra("ID_SESION");
        cantActA =  Integer.parseInt( getIntent().getStringExtra("CANT_A")) ;
        cantActB =  Integer.parseInt( getIntent().getStringExtra("CANT_B")) ;
        Log.w("ID sesion",cantActA +"");

        firestore.collection("historial").whereEqualTo("id_sesion", idSesion).get()
                .addOnCompleteListener(task -> {
                    //ArrayList<String> integrantes = new ArrayList<>();
                    Set<String> integrantes = new HashSet<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        integrantes.add(document.getString("id_usuario"));
                        // Log.w("Integrantes", document.getString("id_usuario"));
                        // Log.w("Integrantes 0", integrantes.get(0));
                        //Historial historial =  document.toObject(Historial.class);

                    }
                    //Set<String> integrantesFiltro = new HashSet<>(integrantes);

                    llenarSpiner(new ArrayList<>(integrantes));
                });



    }

    public void llenarSpiner(ArrayList<String> datos) {
        if (!datos.isEmpty()) {
            firestore.collection("usuario").whereIn(FieldPath.documentId(), datos).get().addOnCompleteListener(task -> {
                ArrayList<String> integrantes = new ArrayList<>();
                 usuarios = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    integrantes.add(document.getString("nombre"));
                    usuarios.add(document.toObject(Usuario.class)) ;
                }
                acListaIntegrantes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, integrantes));
                seleccionarItem(usuarios);
            });
        }else {
            tlIntegrantes.setPlaceholderText("No hay integrantes en esta sesi??n");
           // acListaIntegrantes.setHint("No hay integrantes en esta sesi??n");
        }
    }

    public void seleccionarItem(ArrayList<Usuario> usuarios){
        acListaIntegrantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int item, long l) {
                Log.w("HISTORIAL", item+"");
                firestore.collection("historial").whereEqualTo("id_usuario", usuarios.get(item).getId())
                        .whereEqualTo("id_sesion", idSesion).orderBy("fecha_realizado").get().addOnCompleteListener(task -> {
                        List<BarEntry> entradas = new ArrayList<>();
                            List<BarEntry> entradas2 = new ArrayList<>();
                        int orden1 = 1;
                        int orden2 = 1;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                int aciertos = Integer.parseInt(document.getString("aciertos"));
                                if(document.getString("tipo_actividad").equals("1")){

                                    entradas.add(new BarEntry(orden1, aciertos));
                                    Log.w("HISTORIAL", orden1+ " "+ aciertos);
                                    orden1++;
                                }else if(document.getString("tipo_actividad").equals("2")){
                                    entradas2.add(new BarEntry(orden2, aciertos));
                                    orden2++;
                                }

                            }
                            graficoChart(entradas, "Cant. aciertos de "+ cantActA+ " ejercicios x activ. ("+entradas.size()+" intentos)", chartActividadA, (long) cantActA);
                            graficoChart(entradas2, "Cant. aciertos de "+ cantActB+" ejercicios x activ. ("+entradas2.size()+" intentos)", chartActividadB,(long) cantActB);
                        });
            }
        });
    }

    public void graficoChart(List<BarEntry> entrada, String etiqueta, BarChart chart , Long valorMax){
        BarDataSet set = new BarDataSet(entrada, etiqueta);
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(set);
        data.setBarWidth(0.9f);
        chart.setData(data);
        chart.setFitBars(true);
        chart.setDescription(null);

        chart.getAxisLeft().setAxisMaximum(valorMax);
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getAxisLeft().setGranularity(1);

        chart.getAxisRight().setAxisMaximum(valorMax);
        chart.getAxisRight().setAxisMinimum(0);
        chart.getAxisRight().setGranularity(1);

        chart.getXAxis().setGranularity(1);
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        chart.fitScreen();
        chart.invalidate();
        chart.refreshDrawableState();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}