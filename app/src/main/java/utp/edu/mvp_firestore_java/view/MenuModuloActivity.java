package utp.edu.mvp_firestore_java.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.Utils.GlobalHistorial;
import utp.edu.mvp_firestore_java.model.Sesion;

public class MenuModuloActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar tbMenuActividad;
    ImageButton ibModuloA, ibModuloB;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    Sesion sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_modulo);

        tbMenuActividad = findViewById(R.id.tbMenuActividad);

        ibModuloA = findViewById(R.id.ibModulo1);
        ibModuloB = findViewById(R.id.ibModulo2);

        setSupportActionBar(tbMenuActividad);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ibModuloA.setOnClickListener(this);
        ibModuloB.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        sesion =  new Sesion();
        String idSesion = getIntent().getStringExtra("ID_SESION");
        firestore.collection("sesion").document(idSesion).get().addOnCompleteListener(task -> {
         sesion = task.getResult().toObject(Sesion.class);
        });

        tbMenuActividad.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SelectorSesionActivity.class));
            }
        });
    }


   /* @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }*/

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        GlobalHistorial globalHistorial;
        switch (view.getId()) {
            case R.id.ibModulo1:
                new GlobalHistorial(auth.getUid(),sesion.getId(),"1",Integer.parseInt(sesion.getCant_actividad1())  ,0);
               // Toast.makeText(this, GlobalHistorial.getIdSesion(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, ModuloAActivity.class);
                startActivity(intent);
                break;
            case R.id.ibModulo2:
                globalHistorial = new GlobalHistorial(auth.getUid(),sesion.getId(),"2",Integer.parseInt(sesion.getCant_actividad2())  ,0);
                //Toast.makeText(this, GlobalHistorial.getIdSesion(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, ModuloBActivity.class));
                break;
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),SelectorSesionActivity.class));
    }

}