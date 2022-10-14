package utp.edu.mvp_firestore_java.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.contract.RegistroContract;
import utp.edu.mvp_firestore_java.presenter.RegistroPresenter;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener, RegistroContract.View {
    RegistroPresenter presenter;

    EditText edNombre, edCorreo, edContrasena, edContrasenaConfirm;
    Button btEnviarRegistro, btRegresaLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edNombre = findViewById(R.id.edNombreRegistro);
        edCorreo = findViewById(R.id.edNombreRegistro);
        edContrasena = findViewById(R.id.edPassRegistro);
        edContrasenaConfirm = findViewById(R.id.edPassConfirmRegistro);
        btEnviarRegistro = findViewById(R.id.btEnviarResgistro);
        btRegresaLogin = findViewById(R.id.btRegresarLogin);

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
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }

    @Override
    public void registroMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public boolean isValidEmail(String emailToReview) {
        if (!emailToReview.matches(String.valueOf(R.string.mail_format))) {
            return false;
        } else {
            return true;
        }
    }
}