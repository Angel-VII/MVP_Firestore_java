package utp.edu.mvp_firestore_java.presenter;

import com.google.firebase.auth.FirebaseAuth;

public class RegistroInteractor {
    RegistroPresenter presenter;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    public RegistroInteractor(RegistroPresenter presenter){
        this.presenter = presenter;
    }
    protected void registrarCuenta(String correo, String contrasena) {
        auth.createUserWithEmailAndPassword(correo, contrasena).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                presenter.registroExito("Registro correcto");
            } else {
                presenter.registroError("Dirección de correo inválida o no existe. Compruebe su contreseña.");
            }

        });

    }
/*
    public void registrarUserBD(String nombre, String rol){
        Usuario usuario = new Usuario(nombre,rol);
    }
*/
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
