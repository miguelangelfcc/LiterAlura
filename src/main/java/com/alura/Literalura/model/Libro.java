package com.alura.Literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")

public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
  //  @Column(unique = true, nullable = false)
    private String titulo;

    private String lenguajes;
    private int descargas;
    private String nombre;
    private int nacimiento;
    private int fallecimiento;

    @ManyToOne
    private Autor autor;


    public Libro(DatosLibro datosLibro, Autor datosAutor){

        this.titulo = datosLibro.titulo();
        this.nombre  = datosAutor.getNombre();
        this.nacimiento = datosAutor.getNacimiento();
        this.fallecimiento=datosAutor.getFallecimiento();
        this.lenguajes = datosLibro.lenguajes().get(0);
        this.descargas = datosLibro.descargas();

    }

    public Libro() {

    }

    public int getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(int nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(int fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public String getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(String lenguajes) {
        this.lenguajes = lenguajes;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return
                "Titulo='" + titulo + '\'' +
                             ", Autor=" + nombre +
                             ", Lenguajes=" + lenguajes +
                        ", Cantidad de Descargas='" + descargas + '\'';
    }




}
