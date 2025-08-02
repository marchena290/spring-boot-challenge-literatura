package com.aluracursos.literatura.model;

import jakarta.persistence.*;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer numeroDescargas;
    @ManyToMany(cascade = CascadeType.MERGE, fetch =  FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"), // Columna en la tabla intermedia que apunta a la PK de Libro
            inverseJoinColumns  = @JoinColumn(name = "autor_id") // Columna en la tabla intermedia que apunta a la PK de Autor
    )
    private List<Autor> autores = new ArrayList<>();


    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();

        if (datosLibro.idiomas() !=null && !datosLibro.idiomas().isEmpty()){
            this.idioma = String.join(",", datosLibro.idiomas());
        } else {
            this.idioma = "desconocido";
        }

        // Manejar posible null de descargas y convertir a Integer
       this.numeroDescargas = datosLibro.numeroDescargas() !=null ? datosLibro.numeroDescargas().intValue() : 0;

        // Convertir List<DatosAutor> a List<Autor> usando un stream y el constructor de Autor
        if (datosLibro.autores() !=null){
            this.autores = datosLibro.autores().stream()
                    .map(Autor::new)// Esto asume que 'Autor' tiene un constructor que acepta 'DatosAutor'
                    .collect(Collectors.toList());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        String autoresNombres = autores.stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining());
        return String.format(
                "----- LIBRO -----%n" +
                        "Título: %s%n" +
                        "Autor(es): %s%n" +
                        "Idioma: %s%n" +
                        "Número de Descargas: %d%n" +
                        "-----------------%n",
                titulo,
                autoresNombres,
                idioma,
                numeroDescargas
        );
    }
}
