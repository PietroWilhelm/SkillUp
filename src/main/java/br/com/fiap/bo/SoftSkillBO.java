package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.SoftSkillDAO;
import br.com.fiap.to.SoftSkill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SoftSkillBO {

    // Inserir notificação
    public String inserirSoftSkill(SoftSkill softSkill) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        SoftSkillDAO softSkillDAO = new SoftSkillDAO(con);
        String resultado = softSkillDAO.inserir(softSkill);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Atualizar SoftSkill
    public String atualizarSoftSkill(SoftSkill softSkill) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        SoftSkillDAO softSkillDAO = new SoftSkillDAO(con);
        String resultado = softSkillDAO.alterar(softSkill);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Excluir SoftSkill
    public String excluirSoftSkillo(SoftSkill softSkill) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        SoftSkillDAO softSkillDAO = new SoftSkillDAO(con);
        String resultado = softSkillDAO.excluir(softSkill);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Buscar SoftSkill por ID
    public String buscarSoftSkillPorId(SoftSkill softSkill) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        SoftSkillDAO softSkillDAO = new SoftSkillDAO(con);
        String resultado = softSkillDAO.listarUm(softSkill);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    //  Listar todas notificações SoftSkills
    public List<SoftSkill> listarTodasSoftSkills() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        SoftSkillDAO softSkillDAO = new SoftSkillDAO(con);
        List<SoftSkill> resultado = softSkillDAO.listarTodos();
        ConnectionFactory.closeConnection();
        return resultado;
    }

}
