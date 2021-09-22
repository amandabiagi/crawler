package the.biagi.crawler.model;


import java.util.ArrayList;
import java.util.List;

public class Filme {

        private String nomeFilme;
        private Double nota;
        private List<String> direcao;
        private List<String> elencoPrincipal;
        private String comentarioPositivo;

    public Filme(String nomeFilme, Double nota, List<String> direcao, List<String> elencoPrincipal, String comentarioPositivo) {
        this.nomeFilme = nomeFilme;
        this.nota = nota;
        this.direcao = new ArrayList<>();
        this.elencoPrincipal = new ArrayList<>();
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

    public List<String> getDirecao() {
        return direcao;
    }

    public void setDirecao(List<String> direcao) {
        this.direcao = direcao;
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
                    "Direção: " + direcao + "\n" +
                    "Elenco principal: " + elencoPrincipal + "\n" +
                    "Comentário positivo: " + comentarioPositivo + "\n" +
                    "----------------------------------------------------\n";
        }
}
