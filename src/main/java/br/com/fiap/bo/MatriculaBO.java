package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.MatriculaDAO;
import br.com.fiap.dao.SoftSkillDAO;
import br.com.fiap.to.Matricula;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MatriculaBO {
    private Connection con;

    public void inserir(Matricula matricula) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        MatriculaDAO dao = new MatriculaDAO(con); // corrigido aqui
        dao.inserir(matricula);
        ConnectionFactory.closeConnection();
    }


    public List<Matricula> listarTodos() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        MatriculaDAO dao = new MatriculaDAO(con);
        List<Matricula> matriculas = dao.listarTodos();
        ConnectionFactory.closeConnection();
        return matriculas;
    }

    public void excluir(int idMatricula) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        MatriculaDAO dao = new MatriculaDAO(con);
        dao.excluir(idMatricula);
        ConnectionFactory.closeConnection();
    }

    public void atualizar(Matricula matricula) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        MatriculaDAO dao = new MatriculaDAO(con);
        dao.atualizar(matricula); // sem capturar retorno
        ConnectionFactory.closeConnection();
    }

}
