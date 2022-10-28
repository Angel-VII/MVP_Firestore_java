package utp.edu.mvp_firestore_java.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.StringValue;

import java.text.SimpleDateFormat;
import java.util.Date;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.model.Historial;
import utp.edu.mvp_firestore_java.view.MenuModuloActivity;

public class DialogModulo {
    private final Activity activity;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;

    public DialogModulo(Activity activity) {
        this.activity = activity;
    }

    public void MensajeDialog(boolean acierto) {

        LayoutInflater inflater = activity.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        final View v = inflater.inflate(R.layout.dialog_actividad_respuesta, null);
        builder.setView(v);
        final AlertDialog dialog = builder.create();

        TextView txRespuesta = v.findViewById(R.id.txRespuestaModulo);
        ImageView ivRespuesta = v.findViewById(R.id.ivRespuestaModulo);

        if (acierto) {
            txRespuesta.setText("¡Buen Trabajo");
            ivRespuesta.setImageResource(R.drawable.vct_mensaje_1);
            int actual = GlobalHistorial.getAciertos();
            GlobalHistorial.setAciertos(++actual);
            //verificaActividad();
            Log.w("clase global", GlobalHistorial.getContadorActividad() +"");

        } else {
            txRespuesta.setText("¡Estuviste cerca!");
            ivRespuesta.setImageResource(R.drawable.vct_mensaje_2);
            //verificaActividad();
        }

        contenidoDialog(v, activity);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    protected void contenidoDialog(View v, Activity activity) {
        int actual = GlobalHistorial.getContadorActividad();
        int total = GlobalHistorial.getTotalActividad();
        GlobalHistorial.setContadorActividad(--actual);
        Button btDialogContinuar = v.findViewById(R.id.btDialogContinuarModulo);
        if(actual>0){
            btDialogContinuar.setText( "CONTINUAR "+"("+(total-actual)+"/"+total+")");
        }else {
            btDialogContinuar.setText( "FINALIZAR "+"("+(total-actual)+"/"+total+")");
            btDialogContinuar.setBackgroundColor(ContextCompat.getColor(activity, R.color.backgroundColor_2));
        }

        btDialogContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificaActividad();
               // Intent intent = new Intent(new Intent(activity, activity.getClass()));
               // activity.startActivity(intent);

            }
        });
    }

    public void verificaActividad(){

        if(GlobalHistorial.getContadorActividad()>0){
            Intent intent = new Intent(new Intent(activity, activity.getClass()));
            activity.startActivity(intent);
        }else{
            registraHistorial();
            Intent intent = new Intent(new Intent(activity, MenuModuloActivity.class));
            intent.putExtra("ID_SESION", GlobalHistorial.getIdSesion());
            activity.startActivity(intent);
            activity.finish();
        }
    }

    public void registraHistorial(){
        firestore = FirebaseFirestore.getInstance();
        Historial historial = new Historial(GlobalHistorial.getIdSesion()
                ,GlobalHistorial.getIdUsuario()
                ,GlobalHistorial.getTipoActividad()
                ,String.valueOf(GlobalHistorial.getAciertos())
                ,fechaActual());

        firestore.collection("historial").document().set(historial);
    }
    public String fechaActual() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date tiempoActual = new Date();
        return formatoFecha.format(tiempoActual);
    }
}
