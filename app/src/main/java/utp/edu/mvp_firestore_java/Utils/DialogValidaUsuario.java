package utp.edu.mvp_firestore_java.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.model.Usuario;
import utp.edu.mvp_firestore_java.view.ListaSesionActivity;
import utp.edu.mvp_firestore_java.view.MenuModuloActivity;
import utp.edu.mvp_firestore_java.view.SelectorSesionActivity;

public class DialogValidaUsuario {
    private Activity activity;
    private FirebaseFirestore firestore;


    public DialogValidaUsuario(Activity activity) {
        this.activity = activity;
    }

    public void validaDatoUsuario(FirebaseUser user) {

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("usuario").document(user.getUid()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                Usuario usuario = document.toObject(Usuario.class);
                if (usuario != null) {
                    //Toast.makeText(activity, usuario.getNombre() + usuario.getRol(), Toast.LENGTH_SHORT).show();
                    ingresarRolUsuario(usuario.getRol());

                } else {
                    dialogDatosUsuario(user);
                }
            } else {
                Toast.makeText(activity, "Hubo un error al registrar usuario", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void dialogDatosUsuario(FirebaseUser user) {

        LayoutInflater inflater = activity.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        final View v = inflater.inflate(R.layout.dialog_usuario, null);
        builder.setView(v);
        final AlertDialog dialog = builder.create();

        EditText edNombreUser = v.findViewById(R.id.edNombreUsuario);
        RadioGroup rgRolUsuario = v.findViewById(R.id.rgRolesUser);
        Usuario usuario = new Usuario(edNombreUser.getText().toString()
                , user.getEmail()
                , rgRolUsuario.getCheckedRadioButtonId() == R.id.rbRolUser1 ? "1" : "2");
        confirmaDatos(v, user, usuario);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void confirmaDatos(View view, FirebaseUser user, Usuario usuario) {
        Button btConfirmaDatos = view.findViewById(R.id.btConfimarDatosUser);
        btConfirmaDatos.setOnClickListener(view1 -> {
            firestore.collection("usuario").document(user.getUid()).set(usuario);
            ingresarRolUsuario(usuario.getRol());
        });
    }

    public void ingresarRolUsuario(String rol) {
        switch (rol) {
            case "1":
                activity.startActivity(new Intent(activity, SelectorSesionActivity.class));
                activity.finish();
                break;
            case "2":
                activity.startActivity(new Intent(activity, ListaSesionActivity.class));
                activity.finish();
                break;
        }
    }

}
