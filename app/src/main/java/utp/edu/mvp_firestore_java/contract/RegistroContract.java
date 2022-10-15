package utp.edu.mvp_firestore_java.contract;

public interface RegistroContract {

    interface View {
        void registroExito(String mensaje);
        void registroError(String mensaje);
    }

    interface Presenter {
        void registroDatos(String nombre, String correo, String contrasena);
        void registroExito(String mensaje);
        void registroError(String mensaje);

    }

}
