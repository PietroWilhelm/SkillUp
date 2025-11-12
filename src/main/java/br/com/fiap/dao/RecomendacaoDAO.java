package br.com.fiap.dao;

import br.com.fiap.to.Recomendacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecomendacaoDAO implements IDAO {

    private Connection con;
    private Recomendacao recomendacao;

    public RecomendacaoDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public String inserir(Object object) {
        recomendacao = (Recomendacao) object;
        String sql = "INSERT INTO SU_RECOMENDACAO " +
                "(VL_RELEVANCIA, DS_MOTIVO, ID_USUARIO, ID_CURSO) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, recomendacao.getValorRelevancia());
            ps.setString(2, recomendacao.getDescricaoMotivo());
            ps.setInt(3, recomendacao.getIdUsuario());
            ps.setInt(4, recomendacao.getIdCurso());


            if (ps.executeUpdate() > 0) {
                return "Recomendação inserida com sucesso!";
            }
            return "Erro ao inserir uma Recomendação!";
        } catch (SQLException e) {
            return "Erro SQL: " + e.getMessage();
        }
    }

    @Override
    public String alterar(Object object) {
        recomendacao = (Recomendacao) object;
        String sql = "UPDATE SU_RECOMENDACAO SET VL_RELEVANCIA = ?, DS_MOTIVO = ?, ID_USUARIO = ?, ID_CURSO = ? WHERE ID_RECOMENDACAO = ? ";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, recomendacao.getValorRelevancia());
            ps.setString(2, recomendacao.getDescricaoMotivo());
            ps.setInt(3, recomendacao.getIdUsuario());
            ps.setInt(4, recomendacao.getIdCurso());
            ps.setInt(5, recomendacao.getIdRecomendacao());

            if (ps.executeUpdate() > 0) {
                return "Recomendação atualizada com sucesso!";
            }
            return "Nenhuma recomendação encontrada!";
        } catch (SQLException e) {
            return "Erro SQL ao atualizar recomendação: " + e.getMessage();
        }
    }


    @Override
    public String excluir(Object object) {
        recomendacao = (Recomendacao) object;
        String sql = "DELETE FROM SU_RECOMENDACAO WHERE ID_RECOMENDACAO=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, recomendacao.getIdRecomendacao());
            if (ps.executeUpdate() > 0) {
                return "recomendação dexcluída com sucesso!";
            }
            return "Nenhuma recomendação encontrada!";
        } catch (SQLException e) {
            return "Erro SQL: " + e.getMessage();
        }
    }

    @Override
    public String listarUm(Object object) {
        recomendacao = (Recomendacao) object;
        String sql = "SELECT * FROM SU_RECOMENDACAO WHERE ID_RECOMENDACAO=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, recomendacao.getIdRecomendacao());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "ID Recomendação: " + rs.getInt("ID_RECOMENDACAO") +
                        "\nValor da Relevancia: " + rs.getInt("VL_RELEVANCIA") +
                        "\nDescrição do motivo: " + rs.getString("DS_MOTIVO") +
                        "\nID Do Usuário: " + rs.getInt("ID_USUARIO") +
                        "\nID Do Curso: " + rs.getInt("ID_CURSO");

            }
            return "Recomendação não encontrada!";
        } catch (SQLException e) {
            return "Erro SQL: " + e.getMessage();
        }
    }

    public List<Recomendacao> listarTodosPorUsuario(int idUsuario) {
        List<Recomendacao> recomendacoes = new ArrayList<>();
        String sql = """
        SELECT r.ID_RECOMENDACAO,
               r.VL_RELEVANCIA,
               r.DS_MOTIVO,
               r.ID_USUARIO,
               r.ID_CURSO,
               u.NM_USUARIO
        FROM SU_RECOMENDACAO r
        JOIN SU_USUARIO u ON r.ID_USUARIO = u.ID_USUARIO
        WHERE r.ID_USUARIO = ?
        ORDER BY r.VL_RELEVANCIA DESC
    """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Recomendacao r = new Recomendacao();
                r.setIdRecomendacao(rs.getInt("ID_RECOMENDACAO"));
                r.setValorRelevancia(rs.getInt("VL_RELEVANCIA"));
                r.setDescricaoMotivo(rs.getString("DS_MOTIVO"));
                r.setIdUsuario(rs.getInt("ID_USUARIO"));
                r.setIdCurso(rs.getInt("ID_CURSO"));
                r.setNomeUsuario(rs.getString("NM_USUARIO")); // novo campo
                recomendacoes.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar recomendações por usuário: " + e.getMessage());
        }

        return recomendacoes;
    }


    public List<Recomendacao> listarTodas() {
        List<Recomendacao> recomendacoes = new ArrayList<>();

        String sql = """
        SELECT r.ID_RECOMENDACAO,
               r.VL_RELEVANCIA,
               r.DS_MOTIVO,
               r.ID_USUARIO,
               r.ID_CURSO,
               u.NM_USUARIO,
               c.NM_CURSO
        FROM SU_RECOMENDACAO r
        JOIN SU_USUARIO u ON r.ID_USUARIO = u.ID_USUARIO
        JOIN SU_CURSO c ON r.ID_CURSO = c.ID_CURSO
        ORDER BY r.ID_RECOMENDACAO
    """;

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Recomendacao r = new Recomendacao();
                r.setIdRecomendacao(rs.getInt("ID_RECOMENDACAO"));
                r.setValorRelevancia(rs.getInt("VL_RELEVANCIA"));
                r.setDescricaoMotivo(rs.getString("DS_MOTIVO"));
                r.setIdUsuario(rs.getInt("ID_USUARIO"));
                r.setIdCurso(rs.getInt("ID_CURSO"));
                r.setNomeUsuario(rs.getString("NM_USUARIO"));
                r.setNomeCurso(rs.getString("NM_CURSO"));
                recomendacoes.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar recomendações: " + e.getMessage());
        }

        return recomendacoes;
    }


}
