package utp.edu.mvp_firestore_java.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.StringValue;

import java.text.SimpleDateFormat;
import java.util.Date;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.model.Sesion;
import utp.edu.mvp_firestore_java.model.Usuario;

public class DialogSesion {
    private Activity activity;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private EditText edNombreSesion;
    private NumberPicker npActividadA;
    private NumberPicker npActividadB;
    private AlertDialog dialog;

    public DialogSesion(Activity activity) {
        this.activity = activity;
    }

    public void dialogDatosUsuario() {
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        LayoutInflater inflater = activity.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        final View v = inflater.inflate(R.layout.dialog_sesion, null);
        builder.setView(v);
        dialog = builder.create();

        edNombreSesion = v.findViewById(R.id.edNombreSesion);
        npActividadA = v.findViewById(R.id.npActividadA);
        npActividadB = v.findViewById(R.id.npActividadB);
        npActividadA.setMinValue(10);
        npActividadA.setMaxValue(30);
        npActividadB.setMinValue(10);
        npActividadB.setMaxValue(30);

        crearSesion(v);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void crearSesion(View view) {
        Button btCrearSesion = view.findViewById(R.id.btConfimarDatosUser);

        btCrearSesion.setOnClickListener(v -> {
            if (!edNombreSesion.getText().toString().equals("")) {
                firestore.collection("sesion").add(objSesion());
                Toast.makeText(activity, "Sesion creada", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                edNombreSesion.setError("Escribe un nombre para la sesión que quiere crear.");
            }
        });
    }
    public Sesion objSesion(){
     Sesion sesion =new Sesion(
                edNombreSesion.getText().toString()
                , auth.getUid()
                , fechaActual()
                , String.valueOf(npActividadA.getValue())
                , String.valueOf(npActividadB.getValue()));
     return sesion;
    }

    public String fechaActual() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date tiempoActual = new Date();
        return formatoFecha.format(tiempoActual);
    }

}
