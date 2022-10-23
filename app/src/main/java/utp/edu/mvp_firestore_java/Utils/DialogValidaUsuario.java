package utp.edu.mvp_firestore_java.Utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import utp.edu.mvp_firestore_java.model.Usuario;
import utp.edu.mvp_firestore_java.view.MenuModuloActivity;

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
                    Toast.makeText(activity, usuario.getNombre() + usuario.getRol(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity, MenuModuloActivity.class);
                    activity.startActivity(intent);
                } else {
                    Toast.makeText(activity, "complete los datos", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
