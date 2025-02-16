package domain;

public class Cliente {
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;

    public Cliente(String cpf, String nome, String endereco, String telefone) {
        this.cpf = cpf;
        this.endereco = endereco;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String toString() {
        return "CPF: " + cpf + "NOME: " + nome + "Endereco: " + endereco + "Telefone: " + telefone;
    }
}
