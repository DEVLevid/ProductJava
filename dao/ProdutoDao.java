package dao;

import db.ConnectionHelper;
import domain.Cliente;
import domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {
    public void save(Produto produto) {
        String sql = "INSERT INTO PRODUTO (ID, NOME, QUANTIDADE, VALOR_UNIT) VALUES (?, ?, ?, ?)";
        try( Connection connection = ConnectionHelper.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, produto.getId());
            pst.setString(2, produto.getNome());
            pst.setDouble(3, produto.getQuantidade());
            pst.setInt(4, produto.getQuantidade());

            pst.execute();
            pst.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> findAll() {
        String sql = "SELECT * FROM PRODUTO;";
        List<Produto> lista = new ArrayList<>();
        try (Connection connection = ConnectionHelper.getConnection()){
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("NOME");
                int quantidade = rs.getInt("QUANTIDADE");
                double valor_unit = rs.getDouble("VALOR_UNIT");
                Produto produto = new Produto(id, nome, quantidade, valor_unit);
                lista.add(produto);
            }
            pst.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Produto findById(int id) {
        String sql = "SELECT * FROM PRODUTO WHERE ID = ?";
        List<Produto> listaProdutos = new ArrayList<>();
        try (Connection connection = ConnectionHelper.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Produto produtoItem = new Produto(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getInt("QUANTIDADE"),
                        rs.getDouble("VALOR_UNIT"));
                listaProdutos.add(produtoItem);
            }
            pst.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Nao foi possivel buscar os Produtos!", e);
        }
        return (Produto) listaProdutos;
    }
}
