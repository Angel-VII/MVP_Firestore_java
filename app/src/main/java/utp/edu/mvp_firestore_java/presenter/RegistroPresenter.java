package utp.edu.mvp_firestore_java.presenter;

import utp.edu.mvp_firestore_java.contract.RegistroContract;

public class RegistroPresenter implements RegistroContract.Presenter {
    RegistroContract.View view;
    RegistroInteractor interactor;

    public RegistroPresenter(RegistroContract.View view) {
        this.view = view;
        interactor= new RegistroInteractor(this);
    }

    @Override
    public void registroDatos(String nombre, String correo, String contrasena) {
         interactor.registrarCuenta(correo,contrasena);
    }

    @Override
    public void registroExito(String mensaje) {
        view.registroExito(mensaje);
    }

    @Override
    public void registroError(String mensaje) {
        view.registroError(mensaje);
    }
}
