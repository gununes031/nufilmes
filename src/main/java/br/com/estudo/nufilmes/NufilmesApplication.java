package br.com.estudo.nufilmes;

import br.com.estudo.nufilmes.principal.Principal;
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

        Principal principal = new Principal();
        principal.ExibeMenu();

//        //desafio
//        ConsumoArquivo consumoArquivo = new ConsumoArquivo();
//        var jsonArq = consumoArquivo.ObterDados("DadosSerie.json", DadosSerie.class);
//        System.out.println(jsonArq);
    }
}
