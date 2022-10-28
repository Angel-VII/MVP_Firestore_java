package utp.edu.mvp_firestore_java.model;

import com.google.firebase.firestore.DocumentId;

public class Usuario {
    @DocumentId
    private String id;
    private String nombre;
    private String correo;
    private String rol;

    public Usuario(){
    }

    public Usuario(String nombre, String correo, String rol) {
        this.nombre = nombre;
        this.rol = rol;
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
