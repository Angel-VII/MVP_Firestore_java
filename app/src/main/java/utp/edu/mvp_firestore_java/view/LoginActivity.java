package utp.edu.mvp_firestore_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.contract.LoginContract;
import utp.edu.mvp_firestore_java.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View {
    private FirebaseAuth auth;
    LoginPresenter presenter;

    EditText edCorreo, edContrasena;
    Button btLogin,btCrearCuenta;

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
    }

    @Override
    public void onClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.btContinuarLogin:
                presenter.loginEmailPass(edCorreo.getText().toString(), edContrasena.getText().toString());
                break;
            case R.id.btCrearCuentaLogin:
                startActivity(new Intent(this,RegistroActivity.class));
                break;
        }
    }

    @Override
    public void loginMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, CategoryListActivity.class));
    }

}