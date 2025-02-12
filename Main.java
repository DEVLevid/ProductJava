import dao.*;
import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        ClienteDao clienteDao = new ClienteDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
        PedidoDao pedidoDao = new PedidoDao();
        ProdutoDao produtoDao = new ProdutoDao();

        while(true) {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Cadastrar Cliente");
            System.out.println("3. Buscar Produto (por ID)");
            System.out.println("4. Listar Produtos Disponíveis");
            System.out.println("5. Efetuar Venda");
            System.out.println("6. Listar Vendas Realizadas");
            System.out.println("7. Listar Clientes");
            System.out.println("8. Listar Funcionários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opc = scn.nextInt();
            scn.nextLine();

            switch (opc) {
                case 1:
                    System.out.print("Id: ");
                    int id = scn.nextInt();
                    System.out.print("Nome: ");
                    String nome = scn.nextLine();
                    System.out.print("Valor Unitário: ");
                    double valorUnit = scn.nextDouble();
                    System.out.print("Quantidade: ");
                    int quantidade = scn.nextInt();
                    Produto product = new Produto(id, nome, quantidade, valorUnit);
                    produtoDao.save(product);
                    break;
                case 2:
                    System.out.print("CPF: ");
                    String cpf = scn.nextLine();
                    System.out.print("Nome: ");
                    String nomeCliente = scn.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scn.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scn.nextLine();
                    Cliente cliente = new Cliente(cpf, nomeCliente, endereco, telefone);
                    clienteDao.save(cliente);
                    break;
                case 3:
                    System.out.print("ID do Produto: ");
                    int produtoId = scn.nextInt();
                    Produto foundProduct = produtoDao.findById(produtoId);
                    if (foundProduct != null) {
                        System.out.println(foundProduct);
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;
                case 4:
                    List<Produto> listaProdutos = produtoDao.findAll();
                    for (Produto p : listaProdutos) {
                        System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Valor: "
                                + p.getValor_unit() + " | Quantidade: " + p.getQuantidade());
                    }
                    break;
                case 5:
                    System.out.print("CPF do Cliente: ");
                    String clienteCpf = scn.nextLine();
                    System.out.print("CPF do Funcionario: ");
                    String funcionarioCpf = scn.nextLine();
                    double totalValue = 0.0;
                    List<ItemPedido> itemPedidos = new ArrayList<>();
                    while (true) {
                        System.out.print("ID do Produto (0 para finalizar): ");

                        if (!scn.hasNextInt()) {
                            System.out.println("Entrada inválida! Digite um número válido.");
                            scn.next();
                            continue;
                        }

                        int prodId = scn.nextInt();

                        if (prodId == 0) {
                            if (itemPedidos.isEmpty()) {
                                System.out.println("Nenhum produto foi adicionado. Venda cancelada.");
                                return;
                            }
                            break;
                        }

                        System.out.print("Quantidade: ");
                        if (!scn.hasNextInt()) {
                            System.out.println("Entrada inválida! Digite um número válido para a quantidade.");
                            scn.next();
                            continue;
                        }
                        int itemQuantidade = scn.nextInt();

                        if (itemQuantidade <= 0) {
                            System.out.println("A quantidade deve ser maior que zero.");
                            continue;
                        }

                        Produto prod = produtoDao.findById(prodId);

                        if (prod == null) {
                            System.out.println("Produto não encontrado. Tente novamente.");
                            continue;
                        }

                        if (prod.getQuantidade() < itemQuantidade) {
                            System.out.println("Quantidade insuficiente em estoque.");
                            continue;
                        }

                        double valor = prod.getValor_unit() * itemQuantidade;
                        totalValue += valor;
                        ItemPedido itemPedido = new ItemPedido(0,0, prodId, itemQuantidade, valor);

                        itemPedidos.add(itemPedido);
                        prod.setQuantidade(prod.getQuantidade() - itemQuantidade);
                    }

                    if (!itemPedidos.isEmpty()) {
                        Pedido pedido = new Pedido(0, clienteCpf, funcionarioCpf, totalValue);
                        pedidoDao.save(pedido);

                        int pedidoId = pedido.getId();

                        for (ItemPedido item : itemPedidos) {
                            item.setPedidoId(pedidoId);
                            itemPedidoDao.save(item);
                        }

                        pedido.setValorTotal(totalValue);
                        pedidoDao.save(pedido);

                        System.out.println("Venda registrada com sucesso!");
                    }
                    break;
                case 6:
                    List<Pedido> pedidos = pedidoDao.findAll();
                    if (pedidos == null || pedidos.isEmpty()) {
                        System.out.println("Nenhuma venda registrada.");
                    } else {
                        for (Pedido dataPedido : pedidos) {
                            System.out.println("\n=========================");
                            System.out.println("Pedido ID: " + dataPedido.getId());
                            System.out.println("Cliente CPF: " + dataPedido.getClienteCpf());
                            System.out.println("Funcionário CPF: " + dataPedido.getFunionarioCpf());
                            System.out.println("Valor Total: R$" + dataPedido.getValorTotal());
                            System.out.println("Itens do Pedido:");

                            List<ItemPedido> items = itemPedidoDao.findOrderById(dataPedido.getId());

                            if (items == null || items.isEmpty()) {
                                System.out.println("   Nenhum item registrado para este pedido.");
                            } else {
                                for (ItemPedido item : items) {
                                    System.out.println("   - Produto ID: " + item.getProdutoId() +
                                            " | Quantidade: " + item.getQuantidade() +
                                            " | Valor: R$" + item.getValor());
                                }
                            }
                        }
                    }
                    break;
                case 7:
                    List<Cliente> clientes = clienteDao.findAll();
                    for (Cliente c : clientes) {
                        System.out.println("CPF: " + c.getCpf() + " | Nome: " + c.getNome());
                    }
                    break;
                case 8:
                    List<Funcionario> funcionarios = funcionarioDao.findAll();
                    for (Funcionario e : funcionarios) {
                        System.out.println("CPF: " + e.getCpf() + " | Nome: " + e.getNome());
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scn.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}