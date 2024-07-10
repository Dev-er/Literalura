package com.br.alura.literalura.repository;

import com.br.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
    List<Livro> findByidiomaContainingIgnoreCase(String idioma);
    Optional<Livro> findBytituloEqualsIgnoreCase(String titulo);
}
