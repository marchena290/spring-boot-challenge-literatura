package com.aluracursos.literatura.repository;

import com.aluracursos.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);
    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.anoNacimiento <= :ano AND (a.anoFallecimiento >= :ano OR a.anoFallecimiento IS NULL)")
    List<Autor> findAutoresVivos(@Param("ano") Integer ano);
}
