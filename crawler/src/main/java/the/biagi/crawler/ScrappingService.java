package the.biagi.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ScrappingService {

    List<Filme> filmes = new ArrayList<>();

    public void nomesDiretores(String url) throws IOException {

        Connection.Response response = Jsoup.connect(url).execute();
        Document document = response.parse();
        Element tabelaFilmes = document.getElementsByClass("ipc-inline-list--inline").first();
        assert tabelaFilmes != null;
        Elements elements = tabelaFilmes.getElementsByTag("li");


        elements.forEach(element -> {
            String href = element.getElementsByTag("a").attr("href");
            String urlDiretor = ("https://www.imdb.com" + href);

            try {
                Connection.Response responseDiretor = Jsoup.connect(urlDiretor).execute();
                Document documentDiretor = responseDiretor.parse();
                Element classDiretor = documentDiretor.getElementsByClass("name-overview").first();
                Elements elementsDiretor = classDiretor.getElementsByTag("h1");

                elementsDiretor.forEach(elementDiretor -> {
                    String nomeDiretor = elementDiretor.getElementsByTag("span").get(0).html();
                    System.out.print("Diretore(s): " + nomeDiretor + "\n");
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    public void nomesElencoPrincipal(String url) throws IOException {
        Connection.Response response = Jsoup.connect(url).execute();
        Document document = response.parse();

        Element tabelaFilmes = document.getElementsByClass("ipc-inline-list ipc-inline-list--show-dividers ipc-inline-list--inline ipc-metadata-list-item__list-content baseAlt").get(2);
        assert tabelaFilmes != null;
        Elements elements = tabelaFilmes.getElementsByTag("li");

        elements.forEach(element -> {
            String nomeElenco = element.getElementsByTag("a").html();
            System.out.print("Elenco principal " + nomeElenco + "\n");
        });

    }

    public void comentarioPositivo(String url) throws IOException {

        Connection.Response response = Jsoup.connect(url).execute();
        Document document = response.parse();
        Element tabelaFilmes = document.getElementsByClass("ipc-inline-list__item ReviewContent__StyledListItem-vlmc3o-1 bUNAEL").first();
        assert tabelaFilmes != null;
        Elements elements = tabelaFilmes.getElementsByTag("a");

        elements.forEach(element -> {
            String href = element.getElementsByTag("a").attr("href");
            String urlComentario = ("https://www.imdb.com" + href);

            try {
                Connection.Response responseComentarios = Jsoup.connect(urlComentario).execute();
                Document documentComentarios = responseComentarios.parse();
                Element classComentarios = documentComentarios.getElementsByClass("lister").first();
                Elements elementsComentarios = classComentarios.getElementsByTag("form");

                elementsComentarios.forEach(elementComentarios -> {
                    String uri = elementComentarios.getElementsByTag("form").attr("action");
                    String uriComentarioFiltrado = ("https://www.imdb.com" + uri + "?sort=helpfulnessScore&dir=desc&ratingFilter=5");

                    try {
                        Connection.Response responseGetComentario = Jsoup.connect(uriComentarioFiltrado).execute();
                        Document documentGetComentarios = responseGetComentario.parse();
                        Element classGetComentario = documentGetComentarios.getElementsByClass("text show-more__control").first();


                        String comentario = classGetComentario.toString();
                        System.out.println(comentario);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public List<String> urls() throws IOException {
        List<String> urls = new ArrayList<>();
        Connection.Response response = Jsoup.connect("https://www.imdb.com/chart/bottom").execute();
        Document document = response.parse();
        Element tabelaFilmes = document.getElementsByClass("lister-list").first();
        Elements elements = tabelaFilmes.getElementsByTag("tr");

        elements.forEach(element -> {
            String href = element.getElementsByTag("a").attr("href");
            String url = ("https://www.imdb.com" + href);
            urls.add(url);
        });
        return urls;
    }

    public List<String> urlsTop10() throws IOException {
        return urls().stream().limit(10).collect(Collectors.toList());
    }

    public void listaTopFilmes() throws IOException {
        Filme filme = new Filme();
        String nome;
        String nota;
        Element classeNome;
        Element classeNota;
        Element classeDiretor;
        Element classeElenco;

        for (Integer i = 0; i < urlsTop10().size(); i++) {
            String urlAtual = urlsTop10().get(i);
            Connection.Response cResponse = Jsoup.connect(urlAtual).execute();
            Document document2 = cResponse.parse();
            classeNome = document2.getElementsByClass("OriginalTitle__OriginalTitleText-jz9bzr-0 llYePj").first();
            if (classeNome == (null)) {
                classeNome = document2.getElementsByClass("TitleHeader__TitleText-sc-1wu6n3d-0 dxSWFG").first();
                nome = classeNome.getElementsByTag("h1").html();
                System.out.println(nome);
                ;
            }
            nome = classeNome.getElementsByTag("div").html();

            classeNota = document2.getElementsByClass("AggregateRatingButton__RatingScore-sc-1ll29m0-1 iTLWoV").first();
            nota = classeNota.getElementsByTag("span").html();
            nomesDiretores(urlAtual);
            nomesElencoPrincipal(urlAtual);
            comentarioPositivo(urlAtual);

            System.out.print("Nome do filme: " + nome + "\n");
            System.out.println("Avaliação: " + nota + "\n");
        }
    }
}
