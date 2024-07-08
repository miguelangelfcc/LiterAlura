package com.alura.Literalura.principal;

//import com.alura.Literalura.Repositorio.AutorRepositorio;
import com.alura.Literalura.Repositorio.AutorRepositorio;
import com.alura.Literalura.Repositorio.LibroRepositorio;
import com.alura.Literalura.model.*;
import com.alura.Literalura.service.ConsumoAPI;
import com.alura.Literalura.service.ConvierteDatos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);

    private ConsumoAPI consumoApi = new ConsumoAPI();

    private final String URL_BASE = "https://gutendex.com/books/?search=";

    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepositorio libroRepositorio;
    private AutorRepositorio autorRepositorio;


    private List <Libro> libros;
    private List <Autor> autores;

    public Principal(LibroRepositorio libroRepositorio, AutorRepositorio autorRepositorio) {
       this.libroRepositorio=libroRepositorio;
       this.autorRepositorio=autorRepositorio;
    }





    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ********************************************************
                    1 - Buscar Libros por Titulo 
                    2 - Lista de Libros registrados
                    3 - Lista de Autores registrados
                    4 - Lista de Autores registrados vivos en un año elegido
                    5 - Lista de libros por idioma
                                                    
                    0 - Salir
                    ********************************************************
                    """;
            System.out.println(menu);

try {
    opcion = teclado.nextInt();
    teclado.nextLine();

    switch (opcion) {
        case 1:
            guardarlibro();
            break;

        case 2:
            buscarLibrosRegistrados();
            break;

        case 3:
            mostrarAutores();
            break;

        case 4:
            mostrarAutoresVivos();
            break;

        case 5:
            librosPorIdioma();
            break;


        case 0:
            System.out.println("Gracias por usar nuestra aplicación...");
            break;

        default:
            System.out.println("Opción inválida");
    }

}

catch(InputMismatchException e)
{
    System.out.println("Ocurrio un error en la elección de la opción");
    opcion = 0;
}

        }

    }

    private Datos buscarLibro() {

        System.out.println("Ingrese nombre del libro");
        String nombreLibro = teclado.nextLine();


            var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
         //   System.out.println(json);
         //   System.out.println(URL_BASE + nombreLibro.replace(" ", "+"));
            var datos = conversor.obtenerDatos(json, Datos.class);
            return datos;




    }

    private void guardarlibro() {
        Datos datos = buscarLibro();
        if (datos.resultados() != null && !datos.resultados().isEmpty())
        {

        DatosLibro datosLibro = datos.resultados().get(0);
        DatosAutor datosAutor = datosLibro.autor().get(0);

        Autor autor = new Autor(datosAutor);
        Libro libro = new Libro(datosLibro, autor);


     //   Optional<Libro> libroBuscado = libroRepositorio.findByTituloContainsIgnoreCase(libro.getTitulo());
         List<Libro> libroBuscado = libroRepositorio.libroBuscado(libro.getTitulo());

         if (libroBuscado != null && !libroBuscado.isEmpty()){


            System.out.println("El libro ya se encuentra agregado en la base de datos");
        }
        else {
            System.out.println("El libro no se encuentra en la base de datos, sera incorporado");
            System.out.println(libro);
             System.out.println(autor);
             libroRepositorio.save(libro);

             List<Autor> autorBuscado = autorRepositorio.autorBuscado(autor.getNombre());

             if (autorBuscado != null && !autorBuscado.isEmpty()) {

             }

             else {
                 autorRepositorio.save(autor);
                  }
        }
    }
        else {
            System.out.println("El libro no fue encontrado en la Aplicación");
        }
    }
        private void buscarLibrosRegistrados() {

        libros = libroRepositorio.findAll();
        libros.forEach(System.out::println);

    }

    private void mostrarAutores() {

        autores = autorRepositorio.findAll();
        autores.forEach(System.out::println);

    }

    private void mostrarAutoresVivos() throws NumberFormatException {
        System.out.println("Ingrese el año que quiere consultar los autores que estaban vivos");
        try {
            int muerte = Integer.parseInt(teclado.nextLine());
            List<Autor> autores = autorRepositorio.mostrarAutoresVivos(muerte);


            autores.forEach((System.out::println));
        }

        catch (NumberFormatException e)
        {
            System.out.println("Ingrese una opción de año valida\n");
        }


    }


    private void librosPorIdioma() {

        System.out.println("Ingrese el idioma de los libros que desea buscar:\n" +
                "Ingles   --- en\n" +
                "Italiano --- it\n" +
                "Español  --- es\n");
        String idioma = teclado.nextLine();
        List<Libro> librosPorIdioma = libroRepositorio.librosPorIdioma(idioma);
        librosPorIdioma.forEach((System.out::println));
    }




}