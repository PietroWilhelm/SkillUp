package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.Usuario;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioBO {

    //  Buscar todos os Usuários
    public ArrayList<Usuario> listarTodos() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection()) {
            UsuarioDAO dao = new UsuarioDAO(con);
            lista = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }
        return lista;
    }

    // Buscar Usuário por ID
    public Usuario buscarUsuarioPorId(int idUsuario) {
        Usuario user = null;
        try (Connection con = ConnectionFactory.getConnection()) {
            UsuarioDAO dao = new UsuarioDAO(con);
            user = dao.buscarPorId(idUsuario);
        } catch (Exception e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }
        return user;
    }

    // Inserir Usuário
    public Usuario cadastrarUsuario(Usuario user) {

        if (user.getDtCadastro() == null) {
            user.setDtCadastro(LocalDate.now());
        }

        try (Connection con = ConnectionFactory.getConnection()) {
            UsuarioDAO dao = new UsuarioDAO(con);
            return dao.inserir(user);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
        }
        return null;
    }

    // Atualizar Usuário
    public Usuario atualizarUsuario(Usuario user) {
        try (Connection con = ConnectionFactory.getConnection()) {
            UsuarioDAO dao = new UsuarioDAO(con);
            return dao.alterar(user);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        }
        return null;
    }

    // Excluir Usuário
    public boolean excluirUsuario(int idUsuario) {
        try (Connection con = ConnectionFactory.getConnection()) {
            UsuarioDAO dao = new UsuarioDAO(con);
            return dao.excluir(idUsuario);
        } catch (Exception e) {
            System.out.println("Erro ao excluir paciente: " + e.getMessage());
        }
        return false;
    }


}
