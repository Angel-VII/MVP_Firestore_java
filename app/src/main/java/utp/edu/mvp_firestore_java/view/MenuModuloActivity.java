package utp.edu.mvp_firestore_java.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import utp.edu.mvp_firestore_java.R;

public class MenuModuloActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar tbMenuActividad;
    ImageButton ibModuloA, ibModuloB;

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
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibModulo1:
                    startActivity(new Intent(this, ModuloAActivity.class));
                break;
            case R.id.ibModulo2:
                startActivity(new Intent(this, ModuloBActivity.class));
                break;
        }
    }
}