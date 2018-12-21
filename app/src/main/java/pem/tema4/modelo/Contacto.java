package pem.tema4.modelo;

import java.io.Serializable;

public class Contacto implements Serializable {


    private String nombre;
    private String direccion;
    private String telefono;
    private String fechaCumple;

    public Contacto(String nombre, String direccion, String telefono, String fechaCumple){
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaCumple = fechaCumple;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaCumple(String fechaCumple) {
        this.fechaCumple = fechaCumple;
    }

    public String getNombre() {

        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFechaCumple() {
        return fechaCumple;
    }
}
