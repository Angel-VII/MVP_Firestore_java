package utp.edu.mvp_firestore_java.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import utp.edu.mvp_firestore_java.R;

public class DialogModulo {
private Activity activity;

public DialogModulo(Activity activity){
    this.activity = activity;
}

    public void MensajeDialog(boolean acierto) {


    LayoutInflater inflater = activity.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        final View v = inflater.inflate(R.layout.layout_dialog_actividad_respuesta, null);
        builder.setView(v);
        final AlertDialog dialog = builder.create();

        TextView txRespuesta = v.findViewById(R.id.txRespuestaModulo );
        ImageView ivRespuesta = v.findViewById(R.id.ivRespuestaModulo);

        if (acierto){
            txRespuesta.setText("¡Buen Trabajo");
            ivRespuesta.setImageResource(R.drawable.vct_mensaje_1);
        }else {
            txRespuesta.setText("¡Estuviste cerca!");
            ivRespuesta.setImageResource(R.drawable.vct_mensaje_2);
        }

        contenidoDialog(v,activity);
        dialog.show();
    }

    protected void contenidoDialog(View v , Activity activity) {
        Button btDialogContinuar = v.findViewById(R.id.btDialogContinuarModulo);
        btDialogContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(activity, activity.getClass()));
                activity.startActivity(intent);

            }
        });
    }

}
