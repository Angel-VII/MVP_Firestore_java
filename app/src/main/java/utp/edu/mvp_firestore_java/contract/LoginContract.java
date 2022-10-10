package utp.edu.mvp_firestore_java.contract;

import android.app.Activity;

public interface LoginContract {
    interface View {
        void loginMensaje(String mensaje);
    }

    interface Presenter {
        void loginEmailPass(String correo, String contrasena);
        void loginMensaje(String mensaje);

    }
    interface Interactor{
        void loginEmailPass(String correo, String contrasena);
    }

}
