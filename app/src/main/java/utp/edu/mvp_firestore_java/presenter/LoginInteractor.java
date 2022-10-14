package utp.edu.mvp_firestore_java.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

import utp.edu.mvp_firestore_java.contract.LoginContract;

public class LoginInteractor  {
    LoginPresenter presenter;
    FirebaseAuth auth;

    public LoginInteractor(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    public void loginEmailPass(String correo, String contrasena){
        auth = FirebaseAuth.getInstance();
        ingresarLogin(correo, contrasena);
    }

    protected void ingresarLogin(String correo, String contrasena) {
        auth.signInWithEmailAndPassword(correo, contrasena).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                presenter.loginExitoMensaje("Inicio de sesión correcto");
            } else {
                presenter.loginErrorMensaje("Dirección de correo inválida o no existe. Compruebe su contreseña.");
            }
        });
    }

}
