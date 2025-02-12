package dao;

import db.ConnectionHelper;
import domain.Cliente;
import domain.Funcionario;
import domain.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {
    public void save(Pedido pedido) {
        String sql = "INSERT INTO PEDIDO (ID, CPF_CLIENTE_FK, CPF_FUNCIONARIO_FK, VALOR_TOTAL) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionHelper.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, pedido.getId());
            pst.setString(2, pedido.getClienteCpf());
            pst.setString(3, pedido.getFunionarioCpf());
            pst.setDouble(4, pedido.getValorTotal());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao salvar o pedido!", e);
        }
    }

    public List<Pedido> findAll() {
        String sql = "SELECT * FROM PEDIDO";
        List<Pedido> pedidos = new ArrayList<>();
        try (Connection connection = ConnectionHelper.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String cpfClienteFk = rs.getString("CPF_CLIENTE_FK");
                String cpfFuncionarioFk = rs.getString("CPF_FUNCIONARIO_FK");
                double valorTotal = rs.getDouble("VALOR_TOTAL");
                Pedido pedido = new Pedido(id, cpfClienteFk, cpfFuncionarioFk, valorTotal);
                pedidos.add(pedido);
            }
            pst.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }
}
