package br.com.estudo.nufilmes.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
