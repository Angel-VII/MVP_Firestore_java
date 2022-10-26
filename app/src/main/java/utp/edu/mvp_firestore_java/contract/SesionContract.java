package utp.edu.mvp_firestore_java.contract;

import utp.edu.mvp_firestore_java.model.Sesion;

public interface SesionContract {
    interface View{
        void addItem(Sesion sesion);
        void updateItem(int position, Sesion sesion);
        void deleteItem(int position);
    }

    interface Presenter{
        void addItemP(Sesion sesion);
        void updateItemP(int position, Sesion sesion);
        void deleteItemP(int position);
    }
}
