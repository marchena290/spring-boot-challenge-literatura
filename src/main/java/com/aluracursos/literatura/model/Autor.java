package com.aluracursos.literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer anoNacimiento;
    private Integer anoFallecimiento;
    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    private List<Libro> libros;

    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombreAutor();
        this.anoNacimiento = datosAutor.anoNacimiento();
        this.anoFallecimiento = datosAutor.anoFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAnoFallecimiento(Integer anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        String nacimiento = (anoNacimiento !=null) ? String.valueOf(anoNacimiento) : "Desconocido";
        String fallecimiento = (anoFallecimiento != null) ? String.valueOf(anoNacimiento) : "Desconocido";
        return String.format(
                "----- AUTOR -----%n" +
                        "Nombre: %s%n" +
                        "Año de Nacimiento: %s%n" +
                        "Año de Fallecimiento: %s%n" +
                        "-----------------%n",
                nombre, // Usar el campo 'nombre' de la entidad
                nacimiento,
                fallecimiento
        );
    }
}
