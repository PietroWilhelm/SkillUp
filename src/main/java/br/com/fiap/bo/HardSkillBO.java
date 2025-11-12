package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.HardSkillDAO;
import br.com.fiap.to.HardSkill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HardSkillBO {

    // Inserir HardSkill
    public String inserir(HardSkill hardSkill) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        HardSkillDAO hardSkillDAO = new HardSkillDAO(con);
        String resultado = hardSkillDAO.inserir(hardSkill);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Atualizar HardSkill
    public String atualizar(HardSkill hardSkill) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        HardSkillDAO hardSkillDAO = new HardSkillDAO(con);
        String resultado = hardSkillDAO.alterar(hardSkill);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Excluir HardSkill
    public String excluir(HardSkill hardSkill) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        HardSkillDAO hardSkillDAO = new HardSkillDAO(con);
        String resultado = hardSkillDAO.excluir(hardSkill);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Buscar HardSkill por ID
    public String buscarPorId(HardSkill hardSkill) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        HardSkillDAO hardSkillDAO = new HardSkillDAO(con);
        String resultado = hardSkillDAO.listarUm(hardSkill);
        ConnectionFactory.closeConnection();
        return resultado;
    }


    public List<HardSkill> listarTodasHardSkills() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        HardSkillDAO hardSkillDAO = new HardSkillDAO(con);
        List<HardSkill> resultado = hardSkillDAO.listarTodos(); // CERTO
        ConnectionFactory.closeConnection();
        return resultado;
    }
}
