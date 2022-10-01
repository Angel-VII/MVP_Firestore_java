package utp.edu.mvp_firestore_java.contract;

public interface LoginContract {
    interface View {
        void loginMensaje(String mensaje);
    }

    interface Presenter {
        void loginEmailPass(String correo, String contraseña);
        void loginMensaje(String mensaje);

    }
    interface Interactor{
        void loginEmailPass(String correo, String contraseña);
    }

}
