package br.com.fiap.dao;

import br.com.fiap.to.UsuarioHardSkill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioHardSkillDAO implements IDAO {

    private Connection con;
    private UsuarioHardSkill usuarioHardSkill;

    public UsuarioHardSkillDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public String inserir(Object object) {
        usuarioHardSkill = (UsuarioHardSkill) object;
        String sql = """
            INSERT INTO SU_USUARIO_HARDSKILL (ID_USUARIO, ID_HARDSKILL, NIVEL_CONHECIMENTO)
            VALUES (?, ?, ?)
        """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuarioHardSkill.getIdUsuario());
            ps.setInt(2, usuarioHardSkill.getIdHardSkill());
            ps.setString(3, usuarioHardSkill.getNivelConhecimento());

            if (ps.executeUpdate() > 0) {
                return "HardSkill inserida no usuário com sucesso!";
            }
            return "Erro ao inserir HardSkill no usuário!";
        } catch (SQLException e) {
            return "Erro de SQL ao inserir HardSkill para o usuário: " + e.getMessage();
        }
    }


    @Override
    public String alterar(Object object) {
        usuarioHardSkill = (UsuarioHardSkill) object;
        // Atualiza apenas o campo NIVEL_CONHECIMENTO
        String sql = """
        UPDATE SU_USUARIO_HARDSKILL
        SET NIVEL_CONHECIMENTO = ?
        WHERE ID_USUARIO = ? AND ID_HARDSKILL = ?
    """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuarioHardSkill.getNivelConhecimento());
            ps.setInt(2, usuarioHardSkill.getIdUsuario());
            ps.setInt(3, usuarioHardSkill.getIdHardSkill());

            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                return "HardSkill do usuário atualizada com sucesso!";
            }
            return "Nenhuma HardSkill encontrada para este usuário!";
        } catch (SQLException e) {
            return "Erro de SQL ao atualizar HardSkill do usuário: " + e.getMessage();
        }
    }



    @Override
    public String excluir(Object object) {
        usuarioHardSkill = (UsuarioHardSkill) object;

        String sql = """
        DELETE FROM SU_USUARIO_HARDSKILL
        WHERE ID_USUARIO = ? AND ID_HARDSKILL = ?
    """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuarioHardSkill.getIdUsuario());
            ps.setInt(2, usuarioHardSkill.getIdHardSkill());

            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                return "HardSkill do usuário excluída com sucesso!";
            }
            return "Nenhuma HardSkill encontrada para este usuário!";
        } catch (SQLException e) {
            return "Erro de SQL ao excluir HardSkill do usuário: " + e.getMessage();
        }
    }


    @Override
    public String listarUm(Object object) {
        usuarioHardSkill = (UsuarioHardSkill) object;
        String sql = """
            SELECT uh.ID_USUARIO, u.NM_USUARIO,
                   uh.ID_HARDSKILL, h.NM_HARDSKILL, uh.NIVEL_CONHECIMENTO
            FROM SU_USUARIO_HARDSKILL uh
            JOIN SU_USUARIO u ON uh.ID_USUARIO = u.ID_USUARIO
            JOIN SU_HARDSKILL h ON uh.ID_HARDSKILL = h.ID_HARDSKILL
            WHERE uh.ID_USUARIO = ? AND uh.ID_HARDSKILL = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuarioHardSkill.getIdUsuario());
            ps.setInt(2, usuarioHardSkill.getIdHardSkill());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return "Usuário: " + rs.getString("NM_USUARIO") +
                        "\nHardSkill: " + rs.getString("NM_HARDSKILL") +
                        "\nNível: " + rs.getString("NIVEL_CONHECIMENTO");
            }
            return "HardSkill do usuário não encontrada!";
        } catch (SQLException e) {
            return "Erro de SQL ao listar HardSkill do usuário: " + e.getMessage();
        }
    }

    public List<UsuarioHardSkill> listarTodos() {
        List<UsuarioHardSkill> lista = new ArrayList<>();

        String sql = """
            SELECT uh.ID_USUARIO, u.NM_USUARIO,
                   uh.ID_HARDSKILL, h.NM_HARDSKILL,
                   uh.NIVEL_CONHECIMENTO
            FROM SU_USUARIO_HARDSKILL uh
            JOIN SU_USUARIO u ON uh.ID_USUARIO = u.ID_USUARIO
            JOIN SU_HARDSKILL h ON uh.ID_HARDSKILL = h.ID_HARDSKILL
            ORDER BY u.NM_USUARIO, h.NM_HARDSKILL
        """;

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UsuarioHardSkill uhs = new UsuarioHardSkill();
                uhs.setIdUsuario(rs.getInt("ID_USUARIO"));
                uhs.setNomeUsuario(rs.getString("NM_USUARIO"));
                uhs.setIdHardSkill(rs.getInt("ID_HARDSKILL"));
                uhs.setNomeHardSkill(rs.getString("NM_HARDSKILL"));
                uhs.setNivelConhecimento(rs.getString("NIVEL_CONHECIMENTO"));
                lista.add(uhs);
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL ao listar todas as HardSkills dos usuários: " + e.getMessage());
        }

        return lista;
    }
}
