package utp.edu.mvp_firestore_java.model;

import com.google.firebase.firestore.DocumentId;

public class Historial {
    @DocumentId
    private String id;
    private String id_sesion;
    private String id_usuario;
    private String tipo_actividad;
    private String aciertos;
    private String fecha_realizado;

    public Historial(String id_sesion, String id_usuario, String tipo_actividad, String aciertos, String fecha_realizado) {
        this.id_sesion = id_sesion;
        this.id_usuario = id_usuario;
        this.tipo_actividad = tipo_actividad;
        this.aciertos = aciertos;
        this.fecha_realizado = fecha_realizado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(String id_sesion) {
        this.id_sesion = id_sesion;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTipo_actividad() {
        return tipo_actividad;
    }

    public void setTipo_actividad(String tipo_actividad) {
        this.tipo_actividad = tipo_actividad;
    }

    public String getAciertos() {
        return aciertos;
    }

    public void setAciertos(String aciertos) {
        this.aciertos = aciertos;
    }

    public String getFecha_realizado() {
        return fecha_realizado;
    }

    public void setFecha_realizado(String fecha_realizado) {
        this.fecha_realizado = fecha_realizado;
    }
}
