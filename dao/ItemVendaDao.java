package dao;

import db.ConnectionHelper;
import domain.ItemVenda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemVendaDao {

    public List<ItemVenda> findByPedidoId(int pedidoId) {
        String sql = "SELECT * FROM ITEM_VENDA WHERE PEDIDO_ID = ?";

        List<ItemVenda> itens = new ArrayList<>();
        try (Connection connection = ConnectionHelper.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, pedidoId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                int produtoId = rs.getInt("PRODUTO_ID");
                int quantidade = rs.getInt("QUANTIDADE");
                double precoUnitario = rs.getDouble("PRECO_UNITARIO");

                ItemVenda itemVenda = new ItemVenda(id, produtoId, quantidade, precoUnitario);
                itens.add(itemVenda);
            }

            pst.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return itens;
    }
}
