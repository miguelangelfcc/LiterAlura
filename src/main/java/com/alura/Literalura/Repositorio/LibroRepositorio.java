package com.alura.Literalura.Repositorio;
import com.alura.Literalura.model.Autor;
import com.alura.Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepositorio extends JpaRepository<Libro, Long> {


//Optional<Libro> findByTituloContainsIgnoreCase(String titulo);

  @Query("SELECT l FROM Libro l WHERE l.titulo=:titulo")
  List<Libro> libroBuscado(String titulo);



  @Query("SELECT l FROM Libro l WHERE l.lenguajes=:lenguajes")
  List<Libro> librosPorIdioma(String lenguajes);



}
