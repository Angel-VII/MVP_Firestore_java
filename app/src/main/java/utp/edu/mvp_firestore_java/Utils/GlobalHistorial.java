package utp.edu.mvp_firestore_java.Utils;

import android.app.Application;

public class GlobalHistorial extends Application {
    private static String idUsuario;
    private static String idSesion;
    private static String tipoActividad;
    private static int totalActividad ;
    private static int contadorActividad;
    private static int aciertos ;

    public GlobalHistorial(String idUsuario, String idSesion, String tipoActividad, int contadorActividad, int aciertos) {
        GlobalHistorial.idUsuario = idUsuario;
        GlobalHistorial.idSesion = idSesion;
        GlobalHistorial.tipoActividad = tipoActividad;
        GlobalHistorial.totalActividad = contadorActividad;
        GlobalHistorial.contadorActividad = contadorActividad;
        GlobalHistorial.aciertos = aciertos;
    }
    public GlobalHistorial(){

    }

    public static int getTotalActividad() {
        return totalActividad;
    }

    public static String getTipoActividad() {
        return tipoActividad;
    }

    public static void setTipoActividad(String tipoActividad) {
        GlobalHistorial.tipoActividad = tipoActividad;
    }

    public static String getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(String idUsuario) {
        GlobalHistorial.idUsuario = idUsuario;
    }

    public static String getIdSesion() {
        return idSesion;
    }

    public static void setIdSesion(String idSesion) {
        GlobalHistorial.idSesion = idSesion;
    }

    public static int getContadorActividad() {
        return contadorActividad;
    }

    public static void setContadorActividad(int contadorActividad) {
        GlobalHistorial.contadorActividad = contadorActividad;
    }

    public static int getAciertos() {
        return aciertos;
    }

    public static void setAciertos(int aciertos) {
        GlobalHistorial.aciertos = aciertos;
    }
}
