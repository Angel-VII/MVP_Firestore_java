package utp.edu.mvp_firestore_java.presenter;

import com.google.firebase.auth.FirebaseAuth;

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
