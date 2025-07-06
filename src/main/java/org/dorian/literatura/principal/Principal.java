package org.dorian.literatura.principal;

import org.dorian.literatura.model.Autor;
import org.dorian.literatura.model.DatosLibro;
import org.dorian.literatura.model.Libro;
import org.dorian.literatura.repository.AutorRepository;
import org.dorian.literatura.repository.LibroRepository;
import org.dorian.literatura.service.API;
import org.dorian.literatura.service.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private API api = new API();
    private LibroRepository repository;
    private AutorRepository repositoryAutor;
    private Mapper mapper = new Mapper();

    public Principal(LibroRepository repository, AutorRepository repositoryAutor) {
        this.repository = repository;
        this.repositoryAutor = repositoryAutor;
    }



    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija la opción a traves de su numero
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idiomas
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdiomas();
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void listarAutoresVivos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un año para ver los autores que estaban vivos: ");

        try {
            int año = Integer.parseInt(scanner.nextLine());

            List<Autor> vivos = new ArrayList<>();
            vivos.addAll(repositoryAutor.findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThan(año, año));
            vivos.addAll(repositoryAutor.findByFechaNacimientoLessThanEqualAndFechaFallecimientoIsNull(año));

            if (vivos.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + año);
            } else {
                System.out.println("Autores vivos en el año " + año + ":");
                vivos.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("El año introducido no es válido.");
        }
    }

    private void listarLibrosPorIdiomas() {
        List<Libro> libros = repository.findAll();

        long librosEnEspanol = libros.stream()
                .filter(libro -> libro.getIdiomas() != null && libro.getIdiomas().contains("es"))
                .count();

        long librosEnIngles = libros.stream()
                .filter(libro -> libro.getIdiomas() != null && libro.getIdiomas().contains("en"))
                .count();

        System.out.println("Libros en español: " + librosEnEspanol);
        System.out.println("Libros en inglés: " + librosEnIngles);
    }

    private void mostrarAutoresRegistrados() {
        System.out.println("Autores registrados:\n ");
        repositoryAutor.findAll().forEach(System.out::println);
    }

    private void mostrarLibrosBuscados() {
        System.out.println("Libros registrados:");
        repository.findAll().forEach(System.out::println);
    }

    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
//        Libro serie = new Libro(datos);
        if (datos == null) return;
        Libro libro = new Libro(datos);
        if(repository.findByIdAPI(libro.getIdAPI()).isEmpty()) {
            repository.save(libro);
        }
        System.out.println(libro);
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = scanner.nextLine();
        var datosLibro = api.obtenerDatos(nombreSerie.replace(" ", "+"));
        DatosLibro datos = datosLibro;
        return datos;
    }


}
