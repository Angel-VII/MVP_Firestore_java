package utp.edu.mvp_firestore_java.presenter;

import com.google.firebase.auth.FirebaseAuth;

public class RegistroInteractor {
    RegistroPresenter presenter;
    FirebaseAuth auth;

    public RegistroInteractor(RegistroPresenter presenter){
        this.presenter = presenter;
    }
    protected void registrarCuenta(String correo, String contrasena) {
        auth.createUserWithEmailAndPassword(correo, contrasena).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                presenter.registroMensaje("Inicio de sesión correcto");
            } else {
                presenter.registroMensaje("Dirección de correo inválida o no existe. Compruebe su contreseña.");
            }

        });
    }
 /*REGISTRO
    protected void compruebaEmail(String correo) {

        auth.fetchSignInMethodsForEmail(correo).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {

            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                if (!task.getResult().getSignInMethods().isEmpty()) {
                    presenter.loginMensaje("Esta direccion de correo ya existe");
                }
            }
        });
    }*/

}
