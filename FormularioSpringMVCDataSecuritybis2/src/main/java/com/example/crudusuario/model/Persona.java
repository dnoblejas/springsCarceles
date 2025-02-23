package com.example.crudusuario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "eventos") // Cambio de "personas" a "eventos" si es necesario
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private String email;
    private String ubicacion; // Nuevo campo para ubicación del evento
    private String artista; // Nuevo campo para artista del evento

    // Constructor vacío obligatorio para JPA
    public Persona() {}

    // Constructor con parámetros
    public Persona(String nombre, String apellidos, String email, String ubicacion, String artista) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.ubicacion = ubicacion;
        this.artista = artista;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getArtista() {
        return artista;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
}
