package utp.edu.mvp_firestore_java.contract;

public interface LoginContract {
    interface View {
        void loginExitoMensaje(String mensaje);
        void loginErrorMensaje(String mensaje);
    }

    interface Presenter {
        void loginEmailPass(String correo, String contrasena);
        void loginExitoMensaje(String mensaje);
        void loginErrorMensaje(String mensaje);
    }



}
