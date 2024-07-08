package com.alura.Literalura;

//import com.alura.Literalura.Repositorio.AutorRepositorio;
import com.alura.Literalura.Repositorio.AutorRepositorio;
import com.alura.Literalura.Repositorio.LibroRepositorio;
import com.alura.Literalura.model.DatosLibro;
import com.alura.Literalura.principal.Principal;
import com.alura.Literalura.service.ConsumoAPI;
import com.alura.Literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepositorio libroRepositorio;
	private AutorRepositorio autorRepositorio;


	public LiteraluraApplication(LibroRepositorio libroRepositorio, AutorRepositorio autorRepositorio) {
		this.libroRepositorio = libroRepositorio;
		this.autorRepositorio = autorRepositorio;
	}

	public static void main(String[] args) {
	SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



		Principal principal = new Principal(libroRepositorio, autorRepositorio);
		principal.muestraElMenu();


	}
}
