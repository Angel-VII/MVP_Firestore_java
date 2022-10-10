package utp.edu.mvp_firestore_java.contract;

public interface RegistroContract {

    interface View {
        void registroMensaje(String mensaje);
    }

    interface Presenter {
        void registroDatos(String nombre, String correo, String contrasena);
        void registroMensaje(String mensaje);

    }
    interface Interactor{
        void registroDatos(String nombre, String correo, String contrasena);
    }
}
