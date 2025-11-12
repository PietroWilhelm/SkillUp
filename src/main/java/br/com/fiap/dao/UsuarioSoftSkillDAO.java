package br.com.fiap.dao;

import br.com.fiap.to.UsuarioSoftSkill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSoftSkillDAO implements IDAO {

    private Connection con;
    private UsuarioSoftSkill usuarioSoftSkill;

    public UsuarioSoftSkillDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public String inserir(Object object) {
        usuarioSoftSkill = (UsuarioSoftSkill) object;
        String sql = "INSERT INTO SU_USUARIO_SOFTSKILL (ID_USUARIO, ID_SOFTSKILL, NIVEL_DOMINIO) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuarioSoftSkill.getIdUsuario());
            ps.setInt(2, usuarioSoftSkill.getIdSoftSkill());
            ps.setString(3, usuarioSoftSkill.getNivelDominio());

            if (ps.executeUpdate() > 0) {
                return "SoftSkill inserida no usuário com sucesso!";
            }
            return "Erro ao inserir SoftSkill no usuário!";
        } catch (SQLException e) {
            return "Erro de SQL ao inserir SoftSkill para o usuário: " + e.getMessage();
        }
    }

    @Override
    public String alterar(Object object) {
        usuarioSoftSkill = (UsuarioSoftSkill) object;
        String sql = """
            UPDATE SU_USUARIO_SOFTSKILL
            SET NIVEL_DOMINIO = ?
            WHERE ID_USUARIO = ? AND ID_SOFTSKILL = ?
        """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuarioSoftSkill.getNivelDominio());
            ps.setInt(2, usuarioSoftSkill.getIdUsuario());
            ps.setInt(3, usuarioSoftSkill.getIdSoftSkill());

            if (ps.executeUpdate() > 0) {
                return "SoftSkill do usuário atualizada com sucesso!";
            }
            return "Nenhuma SoftSkill encontrada para este usuário!";
        } catch (SQLException e) {
            return "Erro de SQL ao atualizar SoftSkill do usuário: " + e.getMessage();
        }
    }

    @Override
    public String excluir(Object object) {
        usuarioSoftSkill = (UsuarioSoftSkill) object;
        String sql = "DELETE FROM SU_USUARIO_SOFTSKILL WHERE ID_USUARIO = ? AND ID_SOFTSKILL = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuarioSoftSkill.getIdUsuario());
            ps.setInt(2, usuarioSoftSkill.getIdSoftSkill());
            if (ps.executeUpdate() > 0) {
                return "SoftSkill do usuário excluída com sucesso!";
            }
            return "Nenhuma SoftSkill encontrada para este usuário!";
        } catch (SQLException e) {
            return "Erro de SQL ao excluir SoftSkill do usuário: " + e.getMessage();
        }
    }

    @Override
    public String listarUm(Object object) {
        usuarioSoftSkill = (UsuarioSoftSkill) object;
        String sql = """
            SELECT u.NM_USUARIO, s.NM_SOFTSKILL, uss.NIVEL_DOMINIO
            FROM SU_USUARIO_SOFTSKILL uss
            JOIN SU_USUARIO u ON uss.ID_USUARIO = u.ID_USUARIO
            JOIN SU_SOFTSKILL s ON uss.ID_SOFTSKILL = s.ID_SOFTSKILL
            WHERE uss.ID_USUARIO = ? AND uss.ID_SOFTSKILL = ?
        """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuarioSoftSkill.getIdUsuario());
            ps.setInt(2, usuarioSoftSkill.getIdSoftSkill());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "Usuário: " + rs.getString("NM_USUARIO") +
                        "\nSoftSkill: " + rs.getString("NM_SOFTSKILL") +
                        "\nNível: " + rs.getString("NIVEL_DOMINIO");
            }
            return "SoftSkill do usuário não encontrada!";
        } catch (SQLException e) {
            return "Erro de SQL ao listar SoftSkill do usuário: " + e.getMessage();
        }
    }

    // Listar todas com JOIN mostrando usuário e soft skill
    public List<UsuarioSoftSkill> listarTodos() {
        List<UsuarioSoftSkill> lista = new ArrayList<>();
        String sql = """
            SELECT uss.ID_USUARIO,
                   u.NM_USUARIO,
                   uss.ID_SOFTSKILL,
                   s.NM_SOFTSKILL,
                   uss.NIVEL_DOMINIO
            FROM SU_USUARIO_SOFTSKILL uss
            JOIN SU_USUARIO u ON uss.ID_USUARIO = u.ID_USUARIO
            JOIN SU_SOFTSKILL s ON uss.ID_SOFTSKILL = s.ID_SOFTSKILL
            ORDER BY u.NM_USUARIO, s.NM_SOFTSKILL
        """;
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UsuarioSoftSkill uss = new UsuarioSoftSkill();
                uss.setIdUsuario(rs.getInt("ID_USUARIO"));
                uss.setNomeUsuario(rs.getString("NM_USUARIO"));
                uss.setIdSoftSkill(rs.getInt("ID_SOFTSKILL"));
                uss.setNomeSoftSkill(rs.getString("NM_SOFTSKILL"));
                uss.setNivelDominio(rs.getString("NIVEL_DOMINIO"));
                lista.add(uss);
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL ao listar todas as SoftSkills dos usuários: " + e.getMessage());
        }
        return lista;
    }

    //  Listar por usuário específico
    public List<UsuarioSoftSkill> listarPorUsuario(int idUsuario) {
        List<UsuarioSoftSkill> lista = new ArrayList<>();
        String sql = """
            SELECT uss.ID_USUARIO,
                   u.NM_USUARIO,
                   uss.ID_SOFTSKILL,
                   s.NM_SOFTSKILL,
                   uss.NIVEL_DOMINIO
            FROM SU_USUARIO_SOFTSKILL uss
            JOIN SU_USUARIO u ON uss.ID_USUARIO = u.ID_USUARIO
            JOIN SU_SOFTSKILL s ON uss.ID_SOFTSKILL = s.ID_SOFTSKILL
            WHERE uss.ID_USUARIO = ?
            ORDER BY s.NM_SOFTSKILL
        """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioSoftSkill uss = new UsuarioSoftSkill();
                uss.setIdUsuario(rs.getInt("ID_USUARIO"));
                uss.setNomeUsuario(rs.getString("NM_USUARIO"));
                uss.setIdSoftSkill(rs.getInt("ID_SOFTSKILL"));
                uss.setNomeSoftSkill(rs.getString("NM_SOFTSKILL"));
                uss.setNivelDominio(rs.getString("NIVEL_DOMINIO"));
                lista.add(uss);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar SoftSkills do usuário: " + e.getMessage());
        }
        return lista;
    }
}
