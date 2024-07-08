package com.alura.Literalura.Repositorio;

import com.alura.Literalura.model.Autor;
import com.alura.Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepositorio extends JpaRepository<Autor, Long > {

    @Query("SELECT l FROM Autor l WHERE l.nombre=:nombre")
    List<Autor> autorBuscado(String nombre);


    @Query("SELECT l FROM Autor l WHERE l.fallecimiento>:muerte AND l.nacimiento<:muerte")
    List<Autor> mostrarAutoresVivos(int muerte);
}
