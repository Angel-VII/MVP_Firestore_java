package utp.edu.mvp_firestore_java.view;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import utp.edu.mvp_firestore_java.R;

public class ModuloBActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener, View.OnClickListener {

    CardView cvDrop1, cvDrop2;
    ImageView ivDrag1, ivDrag2, ivDrop1, ivDrop2;
    FloatingActionButton fbCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_b);

        cvDrop1 = findViewById(R.id.cvDrop1);
        cvDrop2 = findViewById(R.id.cvDrop2);
        ivDrag1 = findViewById(R.id.ivDrag1);
        ivDrag2 = findViewById(R.id.ivDrag2);
        ivDrop1 = findViewById(R.id.ivDrop1);
        ivDrop2 = findViewById(R.id.ivDrop2);
        fbCheck = findViewById(R.id.fbCheck);

        String link = "https://storage.googleapis.com/proyecto-mvp-java.appspot.com/a/";

        Glide.with(this).load(link + 1 + "_" + 1 + ".jpg").into(ivDrag1);
        Glide.with(this).load(link + 1 + "_" + 2 + ".jpg").into(ivDrag2);

        ivDrag1.setTag(1);
        ivDrag2.setTag(2);

        ivDrag1.setOnTouchListener(this);
        ivDrag2.setOnTouchListener(this);
        ivDrop1.setOnClickListener(this);
        ivDrop2.setOnClickListener(this);
        fbCheck.setOnClickListener(this);

        cvDrop1.setOnDragListener(this);
        cvDrop2.setOnDragListener(this);

     /*   cvDrop1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
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

                        Toast.makeText(ModuloBActivity.this, "Entrada ok  " + data.getDescription().getLabel(), Toast.LENGTH_SHORT).show();

                        if (data.getDescription().getLabel().equals("1")) {
                            ivDrop1.setImageDrawable(ivDrag1.getDrawable());
                        } else if (data.getDescription().getLabel().equals("2")) {
                            ivDrop1.setImageDrawable(ivDrag2.getDrawable());
                        }

                    case DragEvent.ACTION_DRAG_ENDED:
                        break;
                }
                return true;
            }
        });*/
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
            case R.id.fbCheck:
                CheckActividad();
                break;
        }
    }

    public void comparaTag(ImageView ivDrop) {

        if (ivDrop.getTag().toString().equals(ivDrag1.getTag().toString())) {
            //  Toast.makeText(ModuloBActivity.this, "Entrada ok  " + ivDrag1.getTag(), Toast.LENGTH_SHORT).show();
            ivDrag1.setVisibility(View.VISIBLE);

        } else if (ivDrop.getTag().toString().equals(ivDrag2.getTag().toString())) {
            ivDrag2.setVisibility(View.VISIBLE);
        }
    }

    public void limpiarCasilla(ImageView ivDrop) {
        ivDrop.setImageDrawable(null);
        ivDrop.setTag(0);
    }

    public void CheckActividad() {
        String ivDropTag1, ivDropTag2;
        ivDropTag1 = ivDrop1.getTag().toString();
        ivDropTag2 = ivDrop2.getTag().toString();

        if (ivDropTag1.equals("0") || ivDropTag2.equals("0")) {
            Toast.makeText(this, "Por favor, arrastre las imágenes en los espacios en blanco ", Toast.LENGTH_SHORT).show();
        } else if (ivDropTag1.equals("1") && ivDropTag2.equals("2")) {
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "InCorrecto", Toast.LENGTH_SHORT).show();
        }
    }

}