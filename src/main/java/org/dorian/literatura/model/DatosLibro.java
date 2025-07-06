package org.dorian.literatura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("id") Integer idAPI,
        @JsonAlias("title") String titulo,
        @JsonAlias("download_count") Integer descargas,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("authors") List<Autor> autor

) {
}
