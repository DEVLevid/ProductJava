package domain;

public class Pedido {
    private int id;
    private String clienteCpf;
    private String funionarioCpf;
    private double valorTotal;

    public Pedido(int id, String clienteCpf, String funionarioCpf, double valorTotal) {
        this.id = id;
        this.clienteCpf = clienteCpf;
        this.funionarioCpf = funionarioCpf;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public String getFunionarioCpf() {
        return funionarioCpf;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public void setFunionarioCpf(String funionarioCpf) {
        this.funionarioCpf = funionarioCpf;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido_Id: " + id + "Cliente: " + clienteCpf + "Funcionario: " + funionarioCpf + "Valor Total: " + valorTotal;
    }
}
