import dao.ClienteDao;
import dao.FuncionarioDao;
import dao.ProdutoDao;
import dao.VendaDao;
import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDao clienteDao = new ClienteDao();
        ProdutoDao produtoDao = new ProdutoDao();
        VendaDao vendaDao = new VendaDao();

        while (true) {
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Cadastrar Cliente");
            System.out.println("3. Buscar Produto pelo ID");
            System.out.println("4. Listar Todos os Produtos");
            System.out.println("5. Efetuar Venda");
            System.out.println("6. Listar Vendas Realizadas");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.print("Digite o id do produto: ");
                    int id = scanner.nextInt();
                    System.out.print("Digite o nome do produto: ");
                    String nomeProduto = scanner.next();
                    System.out.print("Digite o preço do produto: ");
                    double precoProduto = scanner.nextDouble();
                    System.out.print("Digite a quantidade em estoque: ");
                    int quantidadeProduto = scanner.nextInt();

                    Produto produto = new Produto(id, nomeProduto, precoProduto, quantidadeProduto);
                    produtoDao.save(produto);
                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.print("Digite o CPF do cliente: ");
                    String cpfCliente = scanner.nextLine();
                    System.out.print("Digite o endereco do cliente: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Digite o telefone do cliente: ");
                    String telefone = scanner.nextLine();

                    Cliente cliente = new Cliente(cpfCliente, nomeCliente, endereco, telefone);
                    clienteDao.save(cliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 3:
                    System.out.print("Digite o ID do produto: ");
                    int idProduto = scanner.nextInt();
                    Produto produtoEncontrado = produtoDao.findById(idProduto);
                    if (produtoEncontrado != null) {
                        System.out.println("Produto encontrado: " + produtoEncontrado);
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 4:
                    List<Produto> listaProdutos = produtoDao.findAll();
                    for (Produto p : listaProdutos) {
                        System.out.println(p);
                    }
                    break;

//                case 5:
//                    System.out.print("Digite o CPF do cliente: ");
//                    String cpfVenda = scanner.nextLine();
//                    Cliente clienteVenda = clienteDao.findByCpf(cpfVenda);
//                    if (clienteVenda == null) {
//                        System.out.println("Cliente não encontrado.");
//                        break;
//                    }
//
//                    System.out.print("Digite o CPF do vendedor: ");
//                    String cpfVendedor = scanner.nextLine();
//                    Funcionario funcionarioVenda = new FuncionarioDao().findByCpf(cpfVendedor);
//
//                    System.out.print("Digite o número de itens na venda: ");
//                    int numItens = scanner.nextInt();
//                    List<ItemVenda> itensVenda = new ArrayList<>();
//                    for (int i = 0; i < numItens; i++) {
//                        System.out.print("Digite o ID do produto: ");
//                        int produtoId = scanner.nextInt();
//                        System.out.print("Digite a quantidade: ");
//                        int quantidade = scanner.nextInt();
//                        Produto produtoVenda = produtoDao.findById(produtoId);
//                        if (produtoVenda != null && produtoVenda.getQuantidade() >= quantidade) {
//                            double precoUnitario = produtoVenda.getPreco();
//                            ItemVenda itemVenda = new ItemVenda(produtoId, quantidade, precoUnitario);
//                            itensVenda.add(itemVenda);
//                            produtoVenda.setQuantidade(produtoVenda.getQuantidade() - quantidade);
//                            produtoDao.update(produtoVenda);
//                        } else {
//                            System.out.println("Produto não encontrado ou estoque insuficiente.");
//                        }
//                    }
//
//                    Venda venda = new Venda(clienteVenda, funcionarioVenda, itensVenda);
//                    vendaDao.save(venda);
//                    System.out.println("Venda realizada com sucesso!");
//                    break;

                case 6:
                    List<Venda> vendas = vendaDao.findAll();
                    for (Venda v : vendas) {
                        System.out.println(v);
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
