package utp.edu.mvp_firestore_java.presenter;

import utp.edu.mvp_firestore_java.contract.SesionContract;

import utp.edu.mvp_firestore_java.model.Sesion;

public class SesionPresenter implements SesionContract.Presenter {
    SesionContract.View view;
    SesionInteractor interactor;

    public SesionPresenter(final SesionContract.View view ,String filtroUsuario) {
        this.view = view;
        this.interactor = new SesionInteractor(this, filtroUsuario);
    }

    @Override
    public void addItemP(Sesion sesion) {
        view.addItem(sesion);
    }

    @Override
    public void updateItemP(int position, Sesion sesion) {
        view.updateItem(position, sesion);
    }

    @Override
    public void deleteItemP(int position) {
        view.deleteItem(position);
    }
}
