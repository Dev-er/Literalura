package com.br.alura.literalura.service;

import com.br.alura.literalura.repository.AutorRepository;
import com.br.alura.literalura.repository.LivroRepository;
import org.springframework.stereotype.Service;


@Service
public interface InterfaceConversor {
    <T> T obterDados(LivroRepository json, AutorRepository classe);
}
