package com.br.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(Integer id,
                       @JsonAlias("title") String titulo,
                       @JsonAlias("authors") List<AutorDTO> autores,
                       @JsonAlias("languages") String[] idioma,
                       @JsonAlias("download_count") Integer numeroDownloads)

{
}
