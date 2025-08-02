package com.aluracursos.literatura.principal;

import com.aluracursos.literatura.model.*;
import com.aluracursos.literatura.repository.AutorRepository;
import com.aluracursos.literatura.repository.LibroRepository;
import com.aluracursos.literatura.service.ConsumoApi;
import com.aluracursos.literatura.service.ConvierteDatos;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;


    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    buscarLibroRegistrado();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivosPorAno();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    @Transactional
    private void buscarLibroPorTitulo() {
        System.out.println("Escriba el nombre del libro que desea buscar");
        String nombreLibro = teclado.nextLine();
        String json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));

        // 1-Mapear JSON
        ResultadoAPI datosbusqueda = conversor.obtenerDatos(json, ResultadoAPI.class);

        // 2-Procesar y guardar resultados
        if (datosbusqueda != null && datosbusqueda.resultados() != null && !datosbusqueda.resultados().isEmpty()){
            System.out.println("--- Libro encontrados (y Guardando) ---");

            for (DatosLibro datosLibro : datosbusqueda.resultados()){
                Libro libro = new Libro(datosLibro);

                // Logica para autores
                List<Autor> autoresEntidad = new ArrayList<>();
                for (DatosAutor datosAutor : datosLibro.autores()){
                    Optional<Autor> autorExisten = autorRepository.findByNombre(datosAutor.nombreAutor());
                    Autor autor;
                    if (autorExisten.isPresent()){
                        autor = autorExisten.get();
                    } else {
                        autor = new Autor(datosAutor); // Crear nuevo si no existe
                        autorRepository.save(autor); // Guardar el nuevo autor
                    }
                    autoresEntidad.add(autor);
                }
                libro.setAutores(autoresEntidad);

                // 3 Guardar el Libro (Esto guardará/asociará los autores también debido al CascadeType.PERSIST)
                libroRepository.save(libro);
                System.out.println(libro);
            }
        } else {
            System.out.println("No se encontraron libros con ese título");
        }
    }

    private void buscarLibroRegistrado() {
        System.out.println("\n--- Libros Registrados ---");
        List<Libro> libros = libroRepository.findAll();

        if (libros.isEmpty()){
            System.out.println("No hay libros registrados aún");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void mostrarAutoresRegistrados() {
        System.out.println("\n--- Autores Registrados ---");
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()){
            System.out.println("No hay autores registrados aún");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void mostrarAutoresVivosPorAno() {
        System.out.println("\n--- Autores Vivos en un Año Determinado ---");
        System.out.println("Por favor, ingrese el año para buscar autores vivos:");
        Integer añoBuscado = teclado.nextInt();

        List<Autor> autores = autorRepository.findAutoresVivos(añoBuscado);

        if (autores.isEmpty()){
            System.out.println("No se encontraron autores vivos en el año: " + añoBuscado + ".");
        } else {
            System.out.println("----------------------------------------");
            autores.forEach(System.out::println);
            System.out.println("----------------------------------------");
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("\n--- Libros por idioma ---");
        System.out.println("Elige el idioma del libro 'es' o 'en' ");
        String idiomaLibro = teclado.nextLine();

        List<Libro> busquedaIdioma = libroRepository.findByIdiomaIgnoreCase(idiomaLibro);

        if (busquedaIdioma.isEmpty()){
            System.out.println("No se ha encontrado libros con ese idioma");
        } else {
            System.out.println("----------------------------------------");
            busquedaIdioma.forEach(System.out::println);
            System.out.println("----------------------------------------");
        }
    }

}
