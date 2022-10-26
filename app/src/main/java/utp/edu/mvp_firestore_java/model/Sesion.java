package utp.edu.mvp_firestore_java.model;

import com.google.firebase.firestore.DocumentId;

public class Sesion {
    @DocumentId
    private String id;
    private String nombre;
    private String id_usuario;
    private String fecha_creacion;
    private String cant_actividad1;
    private String cant_actividad2;

    public Sesion(String nombre, String id_usuario, String fecha_creacion, String cant_actividad1, String cant_actividad2) {
        this.nombre = nombre;
        this.id_usuario = id_usuario;
        this.fecha_creacion = fecha_creacion;
        this.cant_actividad1 = cant_actividad1;
        this.cant_actividad2 = cant_actividad2;
    }
    public Sesion(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getCant_actividad1() {
        return cant_actividad1;
    }

    public void setCant_actividad1(String cant_actividad1) {
        this.cant_actividad1 = cant_actividad1;
    }

    public String getCant_actividad2() {
        return cant_actividad2;
    }

    public void setCant_actividad2(String cant_actividad2) {
        this.cant_actividad2 = cant_actividad2;
    }
}
