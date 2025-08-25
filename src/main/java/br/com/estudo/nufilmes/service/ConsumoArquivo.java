package br.com.estudo.nufilmes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConsumoArquivo<T> {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T ObterDados(String fileName, Class<T> classe){

        try {
            System.out.println(fileName);
            File file = new File(fileName);
            return mapper.readValue(file, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
