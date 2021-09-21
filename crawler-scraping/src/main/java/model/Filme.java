package model;

import java.util.List;

public class Filme {

    private String nomeFilme;
    private Double nota;
    private List<String> diracao;
    private List<String> elencoPrincipal;
    private String comentarioPositivo;

    public Filme(String nomeFilme, Double nota, List<String> diracao, List<String> elencoPrincipal, String comentarioPositivo) {
        this.nomeFilme = nomeFilme;
        this.nota = nota;
        this.diracao = diracao;
        this.elencoPrincipal = elencoPrincipal;
        this.comentarioPositivo = comentarioPositivo;
    }

    public Filme() {
    }

    public String getNomeFilme(String s) {
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

    public List<String> getDiracao() {
        return diracao;
    }

    public void setDiracao(List<String> diracao) {
        this.diracao = diracao;
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

    @Override
    public String toString() {
        return  "----------------------------------------------------\n" +
                "Nome do Filme: " + nomeFilme + "\n" +
                "Nota: " + nota + "\n" +
                "Direção: " + diracao + "\n" +
                "Elenco principal: " + elencoPrincipal + "\n" +
                "Comentário positivo: " + comentarioPositivo + "\n" +
                "----------------------------------------------------\n";
    }
}
