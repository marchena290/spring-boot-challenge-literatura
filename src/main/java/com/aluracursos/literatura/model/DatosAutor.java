package com.aluracursos.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosAutor(
        @JsonAlias("name") String nombreAutor,
        @JsonAlias("birth_year") Integer anoNacimiento,
        @JsonAlias("death_year") Integer anoFallecimiento) {
}
