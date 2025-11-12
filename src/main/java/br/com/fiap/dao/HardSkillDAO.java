package br.com.fiap.dao;

import br.com.fiap.to.HardSkill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HardSkillDAO implements IDAO {

    private Connection con;
    private HardSkill hardSkill;

    public HardSkillDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public String inserir(Object object) {
        hardSkill = (HardSkill) object;
        String sql = "INSERT INTO SU_HARDSKILL (NM_HARDSKILL, DESCRICAO, CATEGORIA) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hardSkill.getNomeHardSkill());
            ps.setString(2, hardSkill.getDescricaoHardSkill());
            ps.setString(3, hardSkill.getCategoriaHardSkill());

            if (ps.executeUpdate() > 0) {
                return "HardSkill inserida com sucesso!";
            }
            return "Erro ao inserir HardSkill!";
        } catch (SQLException e) {
            return "Erro de SQL ao inserir HardSkill: " + e.getMessage();
        }
    }

    @Override
    public String alterar(Object object) {
        hardSkill = (HardSkill) object;
        String sql = "UPDATE SU_HARDSKILL SET NM_HARDSKILL=?, DESCRICAO=?, CATEGORIA=? WHERE ID_HARDSKILL=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hardSkill.getNomeHardSkill());
            ps.setString(2, hardSkill.getDescricaoHardSkill());
            ps.setString(3, hardSkill.getCategoriaHardSkill());
            ps.setInt(4, hardSkill.getIdHardSkill());

            if (ps.executeUpdate() > 0) {
                return "hardSkill atualizado com sucesso!";
            }
            return "Nenhuma hardSkill encontrada!";
        } catch (SQLException e) {
            return "Erro de SQL ao atualizar suporte: " + e.getMessage();
        }
    }

    @Override
    public String excluir(Object object) {
        hardSkill = (HardSkill) object;
        String sql = "DELETE FROM SU_HARDSKILL WHERE ID_HARDSKILL=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, hardSkill.getIdHardSkill());
            if (ps.executeUpdate() > 0) {
                return "hardSkill excluída com sucesso!";
            }
            return "Nenhuma hardSkill encontrada!";
        } catch (SQLException e) {
            return "Erro de SQL ao excluir suporte: " + e.getMessage();
        }
    }

    @Override
    public String listarUm(Object object) {
        hardSkill = (HardSkill) object;
        String sql = "SELECT * FROM SU_HARDSKILL WHERE ID_HARDSKILL=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, hardSkill.getIdHardSkill());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "ID HardSkill: " + rs.getInt("ID_HARDSKILL") +
                        "\nNome: " + rs.getString("NM_HARDSKILL") +
                        "\nDescrição: " + rs.getString("DESCRICAO") +
                        "\nCategoria: " + rs.getString("CATEGORIA");
            }
            return "HardSkill não encontrada!";
        } catch (SQLException e) {
            return "Erro de SQL ao listar HardSkill: " + e.getMessage();
        }
    }

    // Utilitário extra: listar todos os registros de HardSkills
    public List<HardSkill> listarTodos() {
        List<HardSkill> lista = new ArrayList<>();
        String sql = "SELECT * FROM SU_HARDSKILL ORDER BY ID_HARDSKILL";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HardSkill hs = new HardSkill();
                hs.setIdHardSkill(rs.getInt("ID_HARDSKILL"));
                hs.setNomeHardSkill(rs.getString("NM_HARDSKILL"));
                hs.setDescricaoHardSkill(rs.getString("DESCRICAO"));
                hs.setCategoriaHardSkill(rs.getString("CATEGORIA"));
                lista.add(hs);
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL ao listar HardSkills: " + e.getMessage());
        }
        return lista;
    }
}
