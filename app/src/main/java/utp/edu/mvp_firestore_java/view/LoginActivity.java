package utp.edu.mvp_firestore_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import utp.edu.mvp_firestore_java.R;
import utp.edu.mvp_firestore_java.Utils.DialogValidaUsuario;
import utp.edu.mvp_firestore_java.contract.LoginContract;
import utp.edu.mvp_firestore_java.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View {
    private FirebaseAuth auth;
    LoginPresenter presenter;
    DialogValidaUsuario validaUsuario;

    GoogleSignInClient googleSignInClient;
    GoogleSignInOptions googleSignInOptions;

    EditText edCorreo, edContrasena;
    Button btLogin, btCrearCuenta, btInicioGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edCorreo = findViewById(R.id.edCorreoLogin);
        edContrasena = findViewById(R.id.edContrasenaLogin);
        btLogin = findViewById(R.id.btContinuarLogin);
        btCrearCuenta = findViewById(R.id.btCrearCuentaLogin);
        btInicioGoogle = findViewById(R.id.btInicioGoogle);

        btInicioGoogle.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        btCrearCuenta.setOnClickListener(this);
        presenter = new LoginPresenter(this);
        auth = FirebaseAuth.getInstance();
        validaUsuario = new DialogValidaUsuario(this);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            validaUsuario.validaDatoUsuario(user);
            //startActivity(new Intent(this, MenuModuloActivity.class));
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
            case R.id.btInicioGoogle:
                loginGoogle();
                break;
        }
    }

    @Override
    public void loginExitoMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MenuModuloActivity.class));
    }

    @Override
    public void loginErrorMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

    }

    public void loginGoogle() {

        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, 100);
        //Toast.makeText(this, getString(R.string.default_web_client_id), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);

            if (signInAccountTask.isSuccessful()) {
                // When google sign in successful
                // Initialize string
                String s = "Google sign in successful";

                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                // Initialize sign in account
                try {
                    // Initialize sign in account
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    // Check condition
                    if (googleSignInAccount != null) {
                        // When sign in account is not equal to null
                        // Initialize auth credential
                        AuthCredential authCredential = GoogleAuthProvider
                                .getCredential(googleSignInAccount.getIdToken(), null);
                        // Check credential
                        auth.signInWithCredential(authCredential)
                                .addOnCompleteListener(this, task -> {
                                    if (task.isSuccessful()) {
                                        validaUsuario.validaDatoUsuario(auth.getCurrentUser());
                                        Toast.makeText(this, "Firebase authentication successful", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(this, "Authentication Failed :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}