package br.com.fiap.dao;

import br.com.fiap.to.SoftSkill;
import br.com.fiap.to.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoftSkillDAO implements IDAO {

    private Connection con;
    private SoftSkill softSkill;

    public SoftSkillDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public String inserir(Object object) {
        softSkill = (SoftSkill) object;
        String sql = "INSERT INTO SU_SOFTSKILL (NM_SOFTSKILL, DESCRICAO) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, softSkill.getNomeSofSkill());
            ps.setString(2, softSkill.getDescricaoSofSkill());

            if (ps.executeUpdate() > 0) {
                return "SoftSkill inserida com sucesso!";
            }
            return "Erro ao inserir SoftSkill!";
        } catch (SQLException e) {
            return "Erro de SQL ao inserir SoftSkill: " + e.getMessage();
        }
    }

    @Override
    public String alterar(Object object) {
        softSkill = (SoftSkill) object;
        String sql = "UPDATE SU_SOFTSKILL SET NM_SOFTSKILL=?, DESCRICAO=? WHERE ID_SOFTSKILL=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, softSkill.getNomeSofSkill());
            ps.setString(2, softSkill.getDescricaoSofSkill());
            ps.setInt(3,softSkill.getIdSoftSkill());

            if (ps.executeUpdate() > 0) {
                return "SoftSkill atualizada com sucesso!";
            }
            return "Nenhuma SoftSkill encontrada!";
        } catch (SQLException e) {
            return "Erro de SQL ao atualizar SoftSkill: " + e.getMessage();
        }
    }

    @Override
    public String excluir(Object object) {
        softSkill = (SoftSkill) object;
        String sql = "DELETE FROM SU_SOFTSKILL WHERE ID_SOFTSKILL=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, softSkill.getIdSoftSkill());
            if (ps.executeUpdate() > 0) {
                return "SoftSkill excluída com sucesso!";
            }
            return "Nenhuma SoftSkill encontrada!";
        } catch (SQLException e) {
            return "Erro de SQL ao excluir SoftSkill: " + e.getMessage();
        }
    }

    @Override
    public String listarUm(Object object) {
        softSkill = (SoftSkill) object;
        String sql = "SELECT * FROM SU_SOFTSKILL WHERE ID_SOFTSKILL=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, softSkill.getIdSoftSkill());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "ID SoftSkill: " + rs.getInt("ID_NOTIFICACAO") +
                        "\nNome da SoftSkill: " + rs.getString("NM_SOFTSKILL") +
                        "\nDescrição: " + rs.getString("DESCRICAO");
            }
            return "SoftSkill não encontrada!";
        } catch (SQLException e) {
            return "Erro de SQL ao listar SoftSkill: " + e.getMessage();
        }
    }

    // Listar todas SoftSkills
    public ArrayList<SoftSkill> listarTodos() {
        ArrayList<SoftSkill> softSkills = new ArrayList<>();
        String sql = "SELECT * FROM SU_SOFTSKILL";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SoftSkill sk = new SoftSkill();
                sk.setIdSoftSkill(rs.getInt("ID_SOFTSKILL"));
                sk.setNomeSofSkill(rs.getString("NM_SOFTSKILL"));
                sk.setDescricaoSofSkill(rs.getString("DESCRICAO"));
                softSkills.add(sk);
            }

            System.out.println("SoftSkills listados com sucesso! Total: " + softSkills.size());

        } catch (SQLException e) {
            System.out.println("Erro ao listar SoftSkills: " + e.getMessage());
        }

        return softSkills;
    }
}
