package repository;

import models.TipoTransacao;
import models.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    private Connection conexao;

    public TransacaoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Método 1: Salvar (Escrita)
    public void salvar(Transacao transacao) {
        String sql = "INSERT INTO transacoes (descricao, valor, tipo) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setString(1, transacao.getDescricao());
            ps.setDouble(2, transacao.getValor());
            ps.setString(3, transacao.getTipo().toString());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar no banco: " + e.getMessage());
        }
    }

    // Método 2: Listar (Leitura) - O QUE FALTAVA
    public List<Transacao> listar() {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM transacoes";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                String tipoTexto = rs.getString("tipo");

                TipoTransacao tipo = TipoTransacao.valueOf(tipoTexto);

                Transacao t = new Transacao(descricao, valor, tipo);
                transacoes.add(t);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar transações: " + e.getMessage());
        }

        return transacoes;
    }
}