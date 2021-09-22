package the.biagi.crawler.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import the.biagi.crawler.model.Filme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmeScraping {

    private String urlPrincipal = "https://www.imdb.com/chart/bottom";
    private String urlBase = "https://www.imdb.com";
    private String urlFilter = "?sort=helpfulnessScore&dir=desc&ratingFilter=";

    public List<String> buscandoUrlTodosFilmes() throws IOException {
        List<String> urls = new ArrayList<>();
        Connection.Response response = Jsoup.connect(urlPrincipal).execute();
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

    public List<String> dezPrimeirasUrls() throws IOException {
        return buscandoUrlTodosFilmes().stream().limit(10).collect(Collectors.toList());
    }

    public String buscandoTitulo(Document document) {
        String nomeFilme;
        Element classeNomeFilme = document.getElementsByClass("OriginalTitle__OriginalTitleText-jz9bzr-0 llYePj").first();

        if (classeNomeFilme == (null)) {
            classeNomeFilme = document.getElementsByClass("TitleHeader__TitleText-sc-1wu6n3d-0 dxSWFG").first();
            nomeFilme = classeNomeFilme.getElementsByTag("h1").html();
            String nomeFilmeFormatado = nomeFilme.replace("Original title: ","");
            return nomeFilmeFormatado;
        }
        nomeFilme = classeNomeFilme.getElementsByTag("div").html();
        String nomeFilmeFormatado = nomeFilme.replace("Original title: ","");

        return nomeFilmeFormatado;
    }

    public Double buscandoNota(Document document) {
        Double notaFilme;

        Element classeNota = document.getElementsByClass("AggregateRatingButton__RatingScore-sc-1ll29m0-1 iTLWoV").first();
        notaFilme = Double.parseDouble(classeNota.getElementsByTag("span").html());
        Double notaFormatada = Double.parseDouble(String.format("%.2f",notaFilme ).replace(",","."));
        return notaFormatada;
    }

    public List<String> buscandoDirecao(Document document) {
        List<String> direcao = new ArrayList<>();

        Element classeDirecao = document.getElementsByClass("ipc-inline-list--inline").first();
        Elements listaDiretor = classeDirecao.getElementsByTag("li");

        listaDiretor.forEach(element -> {
            String href = element.getElementsByTag("a").attr("href");
            String urlDiretor = (urlBase + href);

            try {
                Connection.Response responseDiretor = Jsoup.connect(urlDiretor).execute();
                Document documentDiretor = responseDiretor.parse();
                Element classDiretor = documentDiretor.getElementsByClass("name-overview").first();
                Elements elementsDiretor = classDiretor.getElementsByTag("h1");

                elementsDiretor.forEach(elementDiretor -> {
                    String nomeDiretor = elementDiretor.getElementsByTag("span").get(0).html();

                    direcao.add(nomeDiretor);
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        return direcao;
    }

    public List<String> buscandoElencoPrincipal(Document document) {
        List<String> elenco = new ArrayList<>();
        Integer index = 0;

        Element classeCreditos = document.getElementsByClass("PrincipalCredits__PrincipalCreditsPanelWideScreen-hdn81t-0 iGxbgr").first();
        Elements listaSpan = classeCreditos.getElementsByTag("span");

        if (listaSpan.size() == 2) {
            index = 2;
        } else {
            index = 1;
        }

        Element classeElenco = document.getElementsByClass("ipc-metadata-list-item__content-container").get(index);
        Elements listaElenco = classeElenco.getElementsByTag("li");

        listaElenco.forEach(element -> {
            String nomeAtor = element.getElementsByTag("a").html();
            elenco.add(nomeAtor);
        });

        return elenco;
    }

    public String buscandoUrlAvaliacao(Document document) {
        List<String> listaUrlAvaliacao = new ArrayList<>();

        Element classeAvaliacoes = document.getElementsByClass("ipc-inline-list ReviewContent__StyledInlineList-vlmc3o-0 hyrmRe baseAlt").first();
        Elements lista = classeAvaliacoes.getElementsByTag("li");
        lista.forEach(element -> {
            listaUrlAvaliacao.add(urlBase + element.getElementsByTag("a").attr("href"));

        });
        return listaUrlAvaliacao.get(0);
    }

    public String buscandoUrlComFiltro(Document document) throws IOException {
        String urlAvaliacao = buscandoUrlAvaliacao(document);
        List<String> urlComFiltro = new ArrayList<>();
        Integer index = 5;

        Connection.Response responseComentarios = Jsoup.connect(urlAvaliacao).execute();
        Document documentComentarios = responseComentarios.parse();


        Element classeComentarios = documentComentarios.getElementsByClass("lister-list").first();
        Elements listaDivs = classeComentarios.getElementsByTag("div");

        if (listaDivs.size() > 2) {
            Element classeComentarios2 = documentComentarios.getElementsByTag("link").first();
            urlComFiltro.add(classeComentarios2.getElementsByTag("link").attr("href") + urlFilter + index);
            return urlComFiltro.get(0);

        } else {

            index++;
        }
        return urlComFiltro.get(0);

    }

    public String buscandoComentario(Document document) throws IOException {

        String uriComentarioFiltrado = buscandoUrlComFiltro(document);
        Connection.Response responseGetComentario = Jsoup.connect(uriComentarioFiltrado).execute();
        Document documentGetComentarios = responseGetComentario.parse();
        Element classGetComentario = documentGetComentarios.getElementsByClass("text show-more__control").first();
        String comentario = classGetComentario.getElementsByTag("div").html();
        String comentarioFormatado = comentario.replace("<br>", "");

        return comentarioFormatado;

    }

    public List<Filme> criandoObjeto() throws IOException {
        List<Filme> filmes = new ArrayList<>();

        for (Integer i = 0; i < dezPrimeirasUrls().size(); i++) {
            Filme filme = new Filme();
            String urlAtual = dezPrimeirasUrls().get(i);
            Connection.Response response = Jsoup.connect(urlAtual).execute();
            Document document = response.parse();

            filme.setNomeFilme(buscandoTitulo(document));
            filme.setNota(buscandoNota(document));
            filme.setDirecao(buscandoDirecao(document));
            filme.setElencoPrincipal(buscandoElencoPrincipal(document));
            filme.setComentarioPositivo(buscandoComentario(document));
            filmes.add(filme);
        }

        List<Filme> sortedList = filmes.stream()
                .sorted(Comparator.comparingDouble(Filme::getNota))
                .collect(Collectors.toList());

        System.out.println(sortedList);

        return  sortedList;
    }


}
