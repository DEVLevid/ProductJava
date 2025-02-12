package dao;

import db.ConnectionHelper;
import domain.ItemPedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDao {
    public void save(ItemPedido itemPedido) {
        String sql = "INSERT INTO ITEMPEDIDO (ID, ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionHelper.getConnection()){
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, itemPedido.getId());
            pst.setInt(2, itemPedido.getPedidoId());
            pst.setInt(3, itemPedido.getProdutoId());
            pst.setInt(4, itemPedido.getQuantidade());
            pst.setDouble(5, itemPedido.getValor());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao salvar o item do pedido!", e);
        }
    }

    public List<ItemPedido> findOrderById( int pedidoId) {
    String sql = "SELECT * FROM ITEMPEDIDO WHERE ID_PEDIDO_FK = ?";
    List<ItemPedido> itemPedidos = new ArrayList<>();
    try(Connection connection = ConnectionHelper.getConnection();){
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, pedidoId);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
           ItemPedido item = new ItemPedido(
                   rs.getInt("ID"),
                   rs.getInt("ID_PEDIDO_FK"),
                   rs.getInt("ID_PRODUTO_FK"),
                   rs.getInt("QUANTIDADE"),
                   rs.getDouble("VALOR"));
           itemPedidos.add(item);
        }

    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException("Erro ao buscar itens do pedido!", e);
    }
    return itemPedidos;
    }
}
