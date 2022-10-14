package utp.edu.mvp_firestore_java.view;

import android.content.ClipData;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.Utils.DialogModulo;

public class ModuloBActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener, View.OnClickListener {

    CardView cvDrop1, cvDrop2, cvDrop3;
    ImageView ivDrag1, ivDrag2, ivDrag3, ivDrop1, ivDrop2, ivDrop3;
    FloatingActionButton fbCheck;
    DialogModulo dialogModulo;
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LinearLayout.LayoutParams childParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            childParam.weight = 1.5f;
            configuracionLayout(childParam);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            childParam.weight = 1;
            configuracionLayout(childParam);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_b);

        cvDrop1 = findViewById(R.id.cvDrop1);
        cvDrop2 = findViewById(R.id.cvDrop2);
        cvDrop3 = findViewById(R.id.cvDrop3);
        ivDrag1 = findViewById(R.id.ivDrag1);
        ivDrag2 = findViewById(R.id.ivDrag2);
        ivDrag3 = findViewById(R.id.ivDrag3);
        ivDrop1 = findViewById(R.id.ivDrop1);
        ivDrop2 = findViewById(R.id.ivDrop2);
        ivDrop3 = findViewById(R.id.ivDrop3);
        fbCheck = findViewById(R.id.fbCheck);


        String link =  String.valueOf(R.string.linkB);

        Glide.with(this).load(link + 1 + "_" + 1 + ".jpg").into(ivDrag1);
        Glide.with(this).load(link + 1 + "_" + 2 + ".jpg").into(ivDrag2);
        Glide.with(this).load(link + 1 + "_" + 3 + ".jpg").into(ivDrag3);

        ivDrag1.setTag(1);
        ivDrag2.setTag(2);
        ivDrag3.setTag(3);

        ivDrag1.setOnTouchListener(this);
        ivDrag2.setOnTouchListener(this);
        ivDrag3.setOnTouchListener(this);

        ivDrop1.setOnClickListener(this);
        ivDrop2.setOnClickListener(this);
        ivDrop3.setOnClickListener(this);
        fbCheck.setOnClickListener(this);

        cvDrop1.setOnDragListener(this);
        cvDrop2.setOnDragListener(this);
        cvDrop3.setOnDragListener(this);

        dialogModulo = new DialogModulo(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        ClipData data = null;
        View.DragShadowBuilder shadowBuilder = null;
        switch (view.getId()) {
            case R.id.ivDrag1:
                data = ClipData.newPlainText(ivDrag1.getTag().toString(), "1");
                shadowBuilder = new View.DragShadowBuilder(ivDrag1);
                break;
            case R.id.ivDrag2:
                data = ClipData.newPlainText(ivDrag2.getTag().toString(), "2");
                shadowBuilder = new View.DragShadowBuilder(ivDrag2);
                break;
            case R.id.ivDrag3:
                data = ClipData.newPlainText(ivDrag3.getTag().toString(), "3");
                shadowBuilder = new View.DragShadowBuilder(ivDrag3);
                break;
        }

        view.startDrag(data, shadowBuilder, null, 0);
        return false;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (view.getId()) {
            case R.id.cvDrop1:
                eventAction(dragEvent, ivDrop1);
                return true;
            case R.id.cvDrop2:
                eventAction(dragEvent, ivDrop2);
                return true;
            case R.id.cvDrop3:
                eventAction(dragEvent, ivDrop3);
                return true;
        }
        return true;
    }

    public void eventAction(DragEvent dragEvent, ImageView ivDrop) {
        final int action = dragEvent.getAction();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DROP:
                ClipData data = dragEvent.getClipData();
                String tag = data.getDescription().getLabel().toString();
                String dragOrigen = data.getItemAt(0).getText().toString();
                // Toast.makeText(ModuloBActivity.this, "Entrada ok  " + data.getItemAt(0).getText(), Toast.LENGTH_SHORT).show();
                updateDrop(ivDrop, dragOrigen, tag);
            case DragEvent.ACTION_DRAG_ENDED:
                break;
        }
    }

    public void updateDrop(ImageView ivDrop, String dragOrigen, String tag) {
        if (!ivDrop.getTag().toString().equals("0")) {
            comparaTag(ivDrop);
        }
        switch (dragOrigen) {
            case "1":
                ivDrop.setImageDrawable(ivDrag1.getDrawable());
                ivDrop.setTag(tag);
                //Toast.makeText(ModuloBActivity.this, "Entrada ok  " + ivDrop.getTag(), Toast.LENGTH_SHORT).show();
                ivDrag1.setVisibility(View.INVISIBLE);
                break;
            case "2":
                ivDrop.setImageDrawable(ivDrag2.getDrawable());
                ivDrop.setTag(tag);
                ivDrag2.setVisibility(View.INVISIBLE);
                break;
            case "3":
                ivDrop.setImageDrawable(ivDrag3.getDrawable());
                ivDrop.setTag(tag);
                ivDrag3.setVisibility(View.INVISIBLE);
                break;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivDrop1:
                comparaTag(ivDrop1);
                limpiarCasilla(ivDrop1);
                break;
            case R.id.ivDrop2:
                comparaTag(ivDrop2);
                limpiarCasilla(ivDrop2);
                break;
            case R.id.ivDrop3:
                comparaTag(ivDrop3);
                limpiarCasilla(ivDrop3);
                break;
            case R.id.fbCheck:
                checkActividad();
                break;
        }
    }

    public void comparaTag(ImageView ivDrop) {

        if (ivDrop.getTag().toString().equals(ivDrag1.getTag().toString())) {
            //  Toast.makeText(ModuloBActivity.this, "Entrada ok  " + ivDrag1.getTag(), Toast.LENGTH_SHORT).show();
            ivDrag1.setVisibility(View.VISIBLE);
        } else if (ivDrop.getTag().toString().equals(ivDrag2.getTag().toString())) {
            ivDrag2.setVisibility(View.VISIBLE);
        } else if (ivDrop.getTag().toString().equals(ivDrag3.getTag().toString())) {
            ivDrag3.setVisibility(View.VISIBLE);
        }
    }

    public void limpiarCasilla(ImageView ivDrop) {
        ivDrop.setImageDrawable(null);
        ivDrop.setTag(0);
    }

    public void checkActividad() {
        String ivDropTag1, ivDropTag2,ivDropTag3;
        ivDropTag1 = ivDrop1.getTag().toString();
        ivDropTag2 = ivDrop2.getTag().toString();
        ivDropTag3 =ivDrop3.getTag().toString();

        if (ivDropTag1.equals("0") || ivDropTag2.equals("0")|| ivDropTag3.equals("0")) {
            Toast.makeText(this, "Por favor, arrastre las imágenes en los espacios en blanco ", Toast.LENGTH_SHORT).show();
        } else if (ivDropTag1.equals("1") && ivDropTag2.equals("2")&& ivDropTag3.equals("3")) {
            //Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
                dialogModulo.MensajeDialog(true);
        } else {
           // Toast.makeText(this, "InCorrecto", Toast.LENGTH_SHORT).show();
            dialogModulo.MensajeDialog(false);
        }
    }

    public void configuracionLayout(LinearLayout.LayoutParams childParam ){
        findViewById(R.id.espacioCentral).setLayoutParams(childParam);
        findViewById(R.id.espacioSuperior).setLayoutParams(childParam);
        findViewById(R.id.espacioInferior).setLayoutParams(childParam);
    }
    @Override public void onBackPressed() {}
}