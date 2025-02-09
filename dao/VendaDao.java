package dao;

import db.ConnectionHelper;
import domain.Venda;
import domain.Cliente;
import domain.Funcionario;
import domain.ItemVenda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDao {

    public List<Venda> findAll() {
        String sql = "SELECT * FROM PEDIDO";

        List<Venda> vendas = new ArrayList<>();
        try (Connection connection = ConnectionHelper.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int pedidoId = rs.getInt("ID");
                double valorTotal = rs.getDouble("VALOR_TOTAL");
                Cliente cliente = new ClienteDao().findByCpf(rs.getString("CPF_CLIENTE_FK"));
                Funcionario funcionario = new FuncionarioDao().findByCpf(rs.getString("CPF_FUNCIONARIO_FK"));
                List<ItemVenda> itens = new ItemVendaDao().findByPedidoId(pedidoId);

                Venda venda = new Venda(cliente, funcionario, itens);
                venda.setId(pedidoId);
                venda.setValorTotal(valorTotal);
                vendas.add(venda);
            }

            pst.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return vendas;
    }
}
