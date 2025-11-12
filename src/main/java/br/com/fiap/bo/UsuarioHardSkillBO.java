package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.UsuarioHardSkillDAO;
import br.com.fiap.to.UsuarioHardSkill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioHardSkillBO {

    //  Cadastrar Hardskill ao usuário
    public String cadastrarUsuarioHardSkill(UsuarioHardSkill usuarioHardSkill) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        UsuarioHardSkillDAO usuarioHardSkillDAO = new UsuarioHardSkillDAO(con);
        String resultado = usuarioHardSkillDAO.inserir(usuarioHardSkill);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Atualizar hardkskill ao usuário
    public String atualizarUsuarioHardSkill(UsuarioHardSkill usuarioHardSkill) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        UsuarioHardSkillDAO usuarioHardSkillDAO = new UsuarioHardSkillDAO(con);
        String resultado = usuarioHardSkillDAO.alterar(usuarioHardSkill);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Excluir hardkskill ao usuário
    public String excluirUsuarioHardSkill(UsuarioHardSkill usuarioHardSkill) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        UsuarioHardSkillDAO usuarioHardSkillDAO = new UsuarioHardSkillDAO(con);
        String resultado = usuarioHardSkillDAO.excluir(usuarioHardSkill);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Buscarhardkskill do usuário por ID
    public String buscarFaqPorId(int idUsuario) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        UsuarioHardSkill f = new UsuarioHardSkill();
        f.setIdUsuario(idUsuario);
        UsuarioHardSkillDAO usuarioHardSkillDAO = new UsuarioHardSkillDAO(con);
        String resultado = usuarioHardSkillDAO.listarUm(f);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Listar todos os SoftSkill
    public List<UsuarioHardSkill> listarTodosHardskils() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        UsuarioHardSkillDAO usuarioHardSkillDAO = new UsuarioHardSkillDAO(con);
        List<UsuarioHardSkill> lista = usuarioHardSkillDAO.listarTodos();
        ConnectionFactory.closeConnection();
        return lista;
    }
}
