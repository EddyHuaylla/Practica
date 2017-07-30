package com.practica.eddy.myapplication.Beans;

import java.io.Serializable;

/**
 * Created by eddy on 30/07/2017.
 */

public class PersonaBean implements Serializable {

    private String dni,nombre,apellido,sexo;
    private int edad;


    public String toString(){
        return dni+" | " +nombre +" | " +sexo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
