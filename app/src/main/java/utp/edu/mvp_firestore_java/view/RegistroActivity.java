package utp.edu.mvp_firestore_java.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.contract.RegistroContract;
import utp.edu.mvp_firestore_java.model.Usuario;
import utp.edu.mvp_firestore_java.presenter.RegistroPresenter;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener, RegistroContract.View {
    RegistroPresenter presenter;

    EditText edNombre, edCorreo, edContrasena/*, edContrasenaConfirm*/;
    Button btEnviarRegistro, btRegresaLogin;
    RadioGroup rgRolUsuario;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edNombre = findViewById(R.id.edNombreUsuario);
        edCorreo = findViewById(R.id.edCorreoRegistro);
        edContrasena = findViewById(R.id.edPassRegistro);
        //edContrasenaConfirm = findViewById(R.id.edPassConfirmRegistro);
        btEnviarRegistro = findViewById(R.id.btEnviarResgistro);
        btRegresaLogin = findViewById(R.id.btRegresarLogin);
        rgRolUsuario = findViewById(R.id.rgRolesUser);


        presenter = new RegistroPresenter(this);

        btEnviarRegistro.setOnClickListener(this);
        btRegresaLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btEnviarResgistro:
                entradaDatos();
                break;
            case R.id.btRegresarLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    @Override
    public void registroExito(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        String tipoPago = rgRolUsuario.getCheckedRadioButtonId() == R.id.rbRolUser1 ? "1" : "2";
        registrarUserBD(edNombre.getText().toString(), tipoPago);
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void registroError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

    }

    public void registrarUserBD(String nombre, String rol) {
        Usuario usuario = new Usuario(nombre, rol);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        firestore.collection("usuario").document(auth.getUid()).set(usuario);
        auth.signOut();
    }

    public void entradaDatos() {
        if (!edNombre.getText().toString().equals("")
                && !edCorreo.getText().toString().equals("")
                && !edContrasena.getText().toString().equals("")) {
            compruebaCorreoPassword();
        } else {
            Toast.makeText(this, "Complete todos lo campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void compruebaCorreoPassword() {

        if (edCorreo.getText().toString().matches(getString(R.string.mail_format))) {
            if (edContrasena.getText().toString().length() >= 6) {
                presenter.registroDatos(
                        edNombre.getText().toString(),
                        edCorreo.getText().toString(),
                        edContrasena.getText().toString());
            } else {
                edContrasena.setError("Ingrese una contraseña de 6 o más carácteres");
            }
        } else {
            edCorreo.setError("Formato de correo no admitido");
        }

    }

}