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

    EditText edNombre, edCorreo, edContrasena, edContrasenaConfirm;
    Button btEnviarRegistro, btRegresaLogin;
    RadioGroup rgRolUsuario;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edNombre = findViewById(R.id.edNombreRegistro);
        edCorreo = findViewById(R.id.edCorreoRegistro);
        edContrasena = findViewById(R.id.edPassRegistro);
        edContrasenaConfirm = findViewById(R.id.edPassConfirmRegistro);
        btEnviarRegistro = findViewById(R.id.btEnviarResgistro);
        btRegresaLogin = findViewById(R.id.btRegresarLogin);
        rgRolUsuario = findViewById(R.id.rgRolUsuario);


        presenter = new RegistroPresenter(this);

        btEnviarRegistro.setOnClickListener(this);
        btRegresaLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btEnviarResgistro:
                presenter.registroDatos(
                        edNombre.getText().toString(),
                        edCorreo.getText().toString(),
                        edContrasena.getText().toString());
                break;
            case R.id.btRegresarLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    @Override
    public void registroExito(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        String tipoPago = rgRolUsuario.getCheckedRadioButtonId() == R.id.rbRol1 ? "1" : "2";
        registrarUserBD(edNombre.getText().toString(),tipoPago );

        startActivity(new Intent(this,LoginActivity.class));
    }

    @Override
    public void registroError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

    }

    public boolean isValidEmail(String emailToReview) {
        if (!emailToReview.matches(getString(R.string.mail_format))) {
            return false;
        } else {
            return true;
        }
    }

    public void registrarUserBD(String nombre, String rol) {
        Usuario usuario = new Usuario(nombre, rol);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        firestore.collection("usuario").document(auth.getUid()).set(usuario);
        auth.signOut();
    }

}