package com.aluracursos.literatura.repository;

import com.aluracursos.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
    List<Libro> findByIdiomaIgnoreCase(String idioma);
}
