package com.br.alura.literalura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    private Integer id;

    private String nome;

    private Integer anoNascimento;

    private Integer anoFalecimento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Autor(){}

    public Autor(Integer autorId, String autor, Integer anoNascimento, Integer anoFalecimento) {
        this.id = autorId;
        this.nome = autor;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
    }

    public Integer getanoNascimento() {
        return anoNascimento;
    }

    public void setanoNascimento(Integer ano) {
        this.anoNascimento = ano;
    }

    public Integer getanoFalecimento() {
        return anoFalecimento;
    }

    public void setanoFalecimento(Integer ano) {
        this.anoFalecimento = ano;
    }

    @Override
    public String toString() {
        return  "ID: " + this.id + "\n"+
                "NOME: " + this.nome + "\n"+
                "ANO NASCIMENTO: " +  this.anoNascimento + "\n"+
                "ANO FALECIMENTO: " + this.anoFalecimento + "\n"+
                "---------------------------------------------\n";
    }
}
