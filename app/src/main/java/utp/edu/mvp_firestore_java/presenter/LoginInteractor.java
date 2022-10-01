package utp.edu.mvp_firestore_java.presenter;

import com.google.firebase.auth.FirebaseAuth;

import utp.edu.mvp_firestore_java.contract.LoginContract;

public class LoginInteractor implements LoginContract.Interactor {
    LoginPresenter presenter;
    FirebaseAuth auth;

    public LoginInteractor(LoginPresenter presenter) {
        this.presenter = presenter;

    }


    @Override
    public void loginEmailPass(String correo, String contraseña) {
            presenter.loginMensaje(correo+ " " + contraseña);
    }
}
