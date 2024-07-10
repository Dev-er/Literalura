package com.br.alura.literalura.service;

import com.br.alura.literalura.repository.AutorRepository;
import com.br.alura.literalura.repository.LivroRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ConversorDeDados implements InterfaceConversor{

    private ObjectMapper mapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T obterDados(LivroRepository json, AutorRepository classe) {
        return null;
    }
}
