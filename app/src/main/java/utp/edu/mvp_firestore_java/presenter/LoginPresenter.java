package utp.edu.mvp_firestore_java.presenter;

import android.app.Activity;

import utp.edu.mvp_firestore_java.contract.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View view;
    LoginInteractor interactor;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        interactor = new LoginInteractor(this);
    }

    @Override
    public void loginEmailPass(String correo, String contrasena) {
        interactor.loginEmailPass(correo, contrasena);
    }

    @Override
    public void loginMensaje(String mensaje) {
        view.loginMensaje(mensaje);
    }


}
