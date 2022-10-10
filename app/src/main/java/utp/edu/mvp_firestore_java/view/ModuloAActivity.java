package utp.edu.mvp_firestore_java.view;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utp.edu.mvp_firestore_java.R;

public class ModuloAActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivCentral;
    ImageButton ibOpcion1, ibOpcion2, ibOpcion3, ibOpcion4;
    LinearLayout linearLayout;
    List<Integer> list;
    int numSeleccionado;

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            linearLayout.setOrientation(LinearLayout.VERTICAL);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_a);

        linearLayout = findViewById(R.id.liLayoutModuloA);
        ivCentral = findViewById(R.id.ivImgA_Central);
        ibOpcion1 = findViewById(R.id.ibOptionA_1);
        ibOpcion2 = findViewById(R.id.ibOptionA_2);
        ibOpcion3 = findViewById(R.id.ibOptionA_3);
        ibOpcion4 = findViewById(R.id.ibOptionA_4);

        list = listNumPrimeOpcion();
        numSeleccionado = numSeleccionado(list);
        int numRandomSecundario = numRandomSecundario();

        String link = "https://storage.googleapis.com/proyecto-mvp-java.appspot.com/a/";

        Glide.with(this).load(link + numSeleccionado + "_" + numRandomSecundario + ".jpg").into(ivCentral);

        Glide.with(this).load(link + list.get(0) + "_" +
                numOpcionSecundario(list.get(0), numSeleccionado, numRandomSecundario) + ".jpg").into(ibOpcion1);
        Glide.with(this).load(link + list.get(1) + "_" +
                numOpcionSecundario(list.get(1), numSeleccionado, numRandomSecundario) + ".jpg").into(ibOpcion2);
        Glide.with(this).load(link + list.get(2) + "_" +
                numOpcionSecundario(list.get(2), numSeleccionado, numRandomSecundario) + ".jpg").into(ibOpcion3);
        Glide.with(this).load(link + list.get(3) + "_" +
                numOpcionSecundario(list.get(3), numSeleccionado, numRandomSecundario) + ".jpg").into(ibOpcion4);

        ibOpcion1.setOnClickListener(this);
        ibOpcion2.setOnClickListener(this);
        ibOpcion3.setOnClickListener(this);
        ibOpcion4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ibOptionA_1:
                coincidencia(list.get(0), numSeleccionado);
                break;
            case R.id.ibOptionA_2:
                coincidencia(list.get(1), numSeleccionado);
                break;
            case R.id.ibOptionA_3:
                coincidencia(list.get(2), numSeleccionado);
                break;
            case R.id.ibOptionA_4:
                coincidencia(list.get(3), numSeleccionado);
                break;
        }

    }

    static public List<Integer> listNumPrimeOpcion() {

        List<Integer> listaNum = new ArrayList<>();
        int totalItems = 5;

        for (int i = 1; i <= totalItems; i++) {
            listaNum.add(i);
        }
        Collections.shuffle(listaNum);

        return listaNum.subList(0, 4);
    }

    static public int numSeleccionado(List<Integer> listNumPrimeOpcion) {
        Collections.shuffle(listNumPrimeOpcion);
        return listNumPrimeOpcion.get(0);
    }


    static public int numOpcionSecundario(int numPrimeOpcion, int numSeleccionado, int numSecundario) {

        if (numPrimeOpcion == numSeleccionado) {
            int selectSecundario = 0;
            do {
                selectSecundario = numRandomSecundario();
            } while (selectSecundario == numSecundario);
            return selectSecundario;
        } else {
            return numRandomSecundario();
        }
    }

    static public int numRandomSecundario() {
        int cantVariacionItems = 3;
        return (int) (Math.random() * cantVariacionItems) + 1;

    }

    public void coincidencia(int numPrimeOpcion, int numSeleccionado) {
        if (numPrimeOpcion == numSeleccionado) {
            Toast.makeText(this, "ACIERTO", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "SIGUE INTENTANDO", Toast.LENGTH_SHORT).show();
        }
    }

}