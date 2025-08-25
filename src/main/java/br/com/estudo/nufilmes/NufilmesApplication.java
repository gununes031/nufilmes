package br.com.estudo.nufilmes;

import br.com.estudo.nufilmes.model.DadosSerie;
import br.com.estudo.nufilmes.service.ConsumoApi;
import br.com.estudo.nufilmes.service.ConsumoArquivo;
import br.com.estudo.nufilmes.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NufilmesApplication implements CommandLineRunner {

	public static void main(String[] args) {

        SpringApplication.run(NufilmesApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        /*var consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados("https://www.omdbapi.com/?apikey=60f8afdd&t=Arrow");
        System.out.println(json);
        ConverteDados conversor = new ConverteDados();
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);*/

        //desafio
        ConsumoArquivo consumoArquivo = new ConsumoArquivo();
        var json = consumoArquivo.ObterDados("DadosSerie.json", DadosSerie.class);
        System.out.println(json);
    }
}
