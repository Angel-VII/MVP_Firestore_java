package utp.edu.mvp_firestore_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.contract.LoginContract;
import utp.edu.mvp_firestore_java.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View {
    private FirebaseAuth auth;
    LoginPresenter presenter;

    EditText edCorreo, edContrasena;
    Button btLogin, btCrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edCorreo = findViewById(R.id.edCorreoLogin);
        edContrasena = findViewById(R.id.edContrasenaLogin);
        btLogin = findViewById(R.id.btContinuarLogin);
        btCrearCuenta = findViewById(R.id.btCrearCuentaLogin);

        btLogin.setOnClickListener(this);
        btCrearCuenta.setOnClickListener(this);
        presenter = new LoginPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(this, MenuModuloActivity.class));
        }
    }

    @Override
    public void onClick(@NonNull View view) {
        String correo = edCorreo.getText().toString();
        String contrasena = edContrasena.getText().toString();
        switch (view.getId()) {
            case R.id.btContinuarLogin:
                if (correo.equals("")) {
                    edCorreo.setError("Por favor, ingrese un correo válido.");
                } else if (contrasena.equals("")) {
                    edContrasena.setError("Por favor, ingrese su contraseña.");
                } else {
                    presenter.loginEmailPass(edCorreo.getText().toString(), edContrasena.getText().toString());
                }
                break;
            case R.id.btCrearCuentaLogin:
                startActivity(new Intent(this, RegistroActivity.class));
                break;
        }
    }

    @Override
    public void loginExitoMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, CategoryListActivity.class));
    }

    @Override
    public void loginErrorMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

    }


}