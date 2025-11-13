package br.com.fiap.bo;

import br.com.fiap.dao.RecomendacaoDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.to.Recomendacao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RecomendacaoBO {

    public String inserir(Recomendacao resultado) throws SQLException, ClassNotFoundException {
        Connection conexao = ConnectionFactory.getConnection();
        RecomendacaoDAO dao = new RecomendacaoDAO(conexao);
        String resposta = dao.inserir(resultado);
        ConnectionFactory.closeConnection();
        return resposta;
    }

    public String atualizar(Recomendacao resultado) throws SQLException, ClassNotFoundException {
        Connection conexao = ConnectionFactory.getConnection();
        RecomendacaoDAO dao = new RecomendacaoDAO(conexao);
        String resposta = dao.alterar(resultado);
        ConnectionFactory.closeConnection();
        return resposta;
    }

    public String excluir(Recomendacao resultado) throws SQLException, ClassNotFoundException {
        Connection conexao = ConnectionFactory.getConnection();
        RecomendacaoDAO dao = new RecomendacaoDAO(conexao);
        String resposta = dao.excluir(resultado);
        ConnectionFactory.closeConnection();
        return resposta;
    }

    public String buscarPorId(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConnectionFactory.getConnection();
        Recomendacao resultado = new Recomendacao();
        resultado.setIdRecomendacao(id);
        RecomendacaoDAO dao = new RecomendacaoDAO(conexao);
        String resposta = dao.listarUm(resultado);
        ConnectionFactory.closeConnection();
        return resposta;
    }

    public List<Recomendacao> listarPorUsuario(int idUsuario) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        RecomendacaoDAO dao = new RecomendacaoDAO(con);
        List<Recomendacao> lista = dao.listarTodosPorUsuario(idUsuario);
        ConnectionFactory.closeConnection();
        return lista;
    }

    public List<Recomendacao> listarTodas() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        RecomendacaoDAO dao = new RecomendacaoDAO(con);
        List<Recomendacao> lista = dao.listarTodas();
        ConnectionFactory.closeConnection();
        return lista;
    }

}
