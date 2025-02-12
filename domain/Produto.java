package domain;

public class Produto {
    private int id;
    private String nome;
    private double valor_unit;
    private int quantidade;

    public Produto(int id, String nome, int quantidade,  double valor_unit) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor_unit = valor_unit;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor_unit() {
        return valor_unit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor_unit(double valor_unit) {
        this.valor_unit = valor_unit;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String toString() {
        return "ProdutoId: " + id + "Nome: " + nome + "Valor Unitario: " + valor_unit + "Quantidade: " + quantidade;
    }
}
