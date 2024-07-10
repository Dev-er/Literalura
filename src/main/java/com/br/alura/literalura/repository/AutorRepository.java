package com.br.alura.literalura.repository;

import com.br.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
    List<Autor> findByanoFalecimentoLessThan(Integer anoFalecimento);
}
