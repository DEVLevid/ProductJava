package domain;

import java.util.List;

public class Venda {
    private int id;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<ItemVenda> itens;
    private double valorTotal;

    public Venda(Cliente cliente, Funcionario funcionario, List<ItemVenda> itens) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.itens = itens;
        this.valorTotal = calcularValorTotal();
    }

    private double calcularValorTotal() {
        double total = 0;
        for (ItemVenda item : itens) {
            total += item.getQuantidade() * item.getPrecoUnitario();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", funcionario=" + funcionario +
                ", itens=" + itens +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
