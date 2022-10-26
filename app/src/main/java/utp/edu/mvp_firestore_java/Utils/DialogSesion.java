package utp.edu.mvp_firestore_java.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.model.Usuario;

public class DialogSesion {
    private Activity activity;

    private FirebaseFirestore firestore;

    public DialogSesion(Activity activity) {
        this.activity = activity;
    }

    public void dialogDatosUsuario() {

        LayoutInflater inflater = activity.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        final View v = inflater.inflate(R.layout.dialog_sesion, null);
        builder.setView(v);
        final AlertDialog dialog = builder.create();

        EditText edNombreSesion = v.findViewById(R.id.edNombreSesion);
        NumberPicker npActividadA = v.findViewById(R.id.npActividadA);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        NumberPicker npActividadB = v.findViewById(R.id.npActividadB);
        npActividadA.setMinValue(10);
        npActividadA.setMaxValue(30);
        npActividadB.setMinValue(10);
        npActividadB.setMaxValue(30);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
