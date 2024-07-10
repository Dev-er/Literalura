package com.br.alura.literalura.model;

import com.br.alura.literalura.dto.LivroDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    private Integer autor_id;

    private String idioma;

    private Integer numeroDownloads;

    public Livro() {
    }

    public Livro(LivroDTO livro) {
        this.titulo = livro.titulo();
        this.autor_id = livro.id();
        this.idioma = String.join(" ", livro.idioma());
        this.numeroDownloads = livro.numeroDownloads();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAutor() {
        return autor_id;
    }

    public void setAutor(Integer autor) {
        this.autor_id = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "TITULO: " + titulo + "\n" +
                "IDIOMA: " + idioma + "\n" +
                "Nº DE DOWNLOADS: " + numeroDownloads + "\n" +
                "----------------------------------" + "\n";
    }
}
