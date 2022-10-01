package utp.edu.mvp_firestore_java.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.contract.LoginContract;
import utp.edu.mvp_firestore_java.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View {
    private FirebaseAuth auth;
    LoginPresenter presenter;
    String mensaje;
    EditText edCorreo;
    EditText edContraseña;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edCorreo = findViewById(R.id.edCorreoLogin);
        edContraseña = findViewById(R.id.edContraseñaLogin);
        btLogin = findViewById(R.id.btContinuarLogin);
        btLogin.setOnClickListener(this);
        presenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btContinuarLogin:
                Log.w("Prueba", edCorreo.getText().toString());
                presenter.loginEmailPass(edCorreo.getText().toString(), edContraseña.getText().toString());
                break;
        }
    }

    @Override
    public void loginMensaje(String mensaje) {
        this.mensaje = mensaje;
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}