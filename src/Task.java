import java.time.LocalDate;

public class Task {
    private String nome;
    private String descricao;
    private LocalDate prazo;
    private int prioridade;
    private String categoria;
    private String status;

    public Task(String nome, String descricao, LocalDate prazo, int prioridade, String categoria, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.prazo = prazo;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("Prioridade: " + prioridade + " - Categoria: " + categoria + " - Nome: " + nome +
                " - Descrição: " + descricao + " - Status: " + status + " - Prazo: | " + prazo + " |");
    }
}