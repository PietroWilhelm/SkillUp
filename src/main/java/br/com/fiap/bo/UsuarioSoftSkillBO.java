package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.UsuarioSoftSkillDAO;
import br.com.fiap.to.UsuarioSoftSkill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioSoftSkillBO {

    // Cadastrar relação Usuário x SoftSkill
    public String cadastrarUsuarioSoftSkill(UsuarioSoftSkill u) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        try {
            UsuarioSoftSkillDAO dao = new UsuarioSoftSkillDAO(con);
            return dao.inserir(u);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    // Atualizar nível da SoftSkill do usuário
    public String atualizarUsuarioSoftSkill(UsuarioSoftSkill u) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        try {
            UsuarioSoftSkillDAO dao = new UsuarioSoftSkillDAO(con);
            return dao.alterar(u);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    // Excluir relação Usuário x SoftSkill
    public String excluirUsuarioSoftSkill(UsuarioSoftSkill u) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        try {
            UsuarioSoftSkillDAO dao = new UsuarioSoftSkillDAO(con);
            return dao.excluir(u);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    // Listar todas as soft skills de todos os usuários (com nomes)
    public List<UsuarioSoftSkill> listarTodos() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        try {
            UsuarioSoftSkillDAO dao = new UsuarioSoftSkillDAO(con);
            return dao.listarTodos();
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    // Listar soft skills de um usuário específico
    public List<UsuarioSoftSkill> listarPorUsuario(int idUsuario) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        try {
            UsuarioSoftSkillDAO dao = new UsuarioSoftSkillDAO(con);
            return dao.listarPorUsuario(idUsuario);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }
}
