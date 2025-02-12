package dao;

import db.ConnectionHelper;
import domain.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {

    public void save(Funcionario funcionario) {
        String sql = "INSERT INTO FUNCIONARIO (CPF, NOME, ENDERECO, TELEFONE) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionHelper.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, funcionario.getCpf());
            pst.setString(2, funcionario.getNome());
            pst.setString(3, funcionario.getEndereco());
            pst.setString(4, funcionario.getTelefone());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao salvar o funcionário!", e);
        }
    }

    public List<Funcionario> findAll() {
        String sql = "SELECT * FROM FUNCIONARIO";
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection connection = ConnectionHelper.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String cpf = rs.getString("CPF");
                String nome = rs.getString("NOME");
                String endereco = rs.getString("ENDERECO");
                String telefone = rs.getString("TELEFONE");
                Funcionario funcionario = new Funcionario(cpf, nome, endereco, telefone);
                funcionarios.add(funcionario);
            }
            pst.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return funcionarios;
    }
}
