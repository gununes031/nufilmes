package br.com.estudo.nufilmes.principal;

import br.com.estudo.nufilmes.model.DadosEpisodio;
import br.com.estudo.nufilmes.model.DadosSerie;
import br.com.estudo.nufilmes.model.DadosTemporada;
import br.com.estudo.nufilmes.model.Episodio;
import br.com.estudo.nufilmes.service.ConsumoApi;
import br.com.estudo.nufilmes.service.ConverteDados;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    Scanner leitura = new Scanner(System.in);
    private static final String ENDERECO = "https://www.omdbapi.com/?apikey=60f8afdd&t=";
    private ConsumoApi consumoApi = new ConsumoApi();
    ConverteDados conversor = new ConverteDados();

    public void ExibeMenu(){
        System.out.println("Digite o nome da serie para buscar:");
        var nomeSerie = leitura.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+"));
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

//        json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=1&episode=1");
//        DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
//        System.out.println(dadosEpisodio);

        List<DadosTemporada> dadosTemporadas = new ArrayList<>();

        for(int i = 1; i<= dados.totalTemporadas(); i++){
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            dadosTemporadas.add(dadosTemporada);
        }

        dadosTemporadas.forEach(System.out::println);

//        for(int i =0 ; i < dados.totalTemporadas(); i++){
//            List<DadosEpisodio> episodiosTemp =  dadosTemporadas.get(i).episodios();
//            for(int j = 0; j < episodiosTemp.size(); j++){
//                System.out.println(episodiosTemp.get(j).titulo());
//            }
//        }

        dadosTemporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));


        List<DadosEpisodio> dadosEpisodios = dadosTemporadas.stream()
                .flatMap(t -> t.episodios().stream())
                        .collect(Collectors.toList());

        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equals("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = dadosTemporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        System.out.println("Digite um ano para filtrar os episodios");
        var ano = leitura.nextInt();
        leitura.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getDataLancamento() !=null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "temporada : " + e.getTemporada() +
                                " Episódio: " + e.getTitulo() +
                                " Data lançamento: " + e.getDataLancamento().format(formatter)
                ));


    }

}
