package the.biagi.crawler;

import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public class Filme {
    private String nomeFilme;
    private Double nota;
    private List<String> diretores;
    private List<String> elencoPrincipal;
    private String comentarioPositivo;

    public Filme(String nomeFilme, Double nota, List<String> diretores, List<String> elencoPrincipal, String comentarioPositivo) {
        this.nomeFilme = nomeFilme;
        this.nota = nota;
        this.diretores = diretores;
        this.elencoPrincipal = elencoPrincipal;
        this.comentarioPositivo = comentarioPositivo;
    }

    public Filme() {
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public List<String> getDiretores() {
        return diretores;
    }

    public void setDiretores(List<String> diretores) {
        this.diretores = diretores;
    }

    public List<String> getElencoPrincipal() {
        return elencoPrincipal;
    }

    public void setElencoPrincipal(List<String> elencoPrincipal) {
        this.elencoPrincipal = elencoPrincipal;
    }

    public String getComentarioPositivo() {
        return comentarioPositivo;
    }

    public void setComentarioPositivo(String comentarioPositivo) {
        this.comentarioPositivo = comentarioPositivo;
    }
}
