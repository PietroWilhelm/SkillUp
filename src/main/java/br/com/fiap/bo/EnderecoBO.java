package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.EnderecoDAO;
import br.com.fiap.to.Endereco;

import java.sql.Connection;
import java.util.ArrayList;

public class EnderecoBO {

    //  Listar todos os Endereços
    public ArrayList<Endereco> listarTodos() {
        Connection con = ConnectionFactory.getConnection();
        EnderecoDAO enderecoDAO = new EnderecoDAO(con);
        ArrayList<Endereco> lista = enderecoDAO.listarTodos();
        ConnectionFactory.closeConnection();
        return lista;
    }

    //  Buscar Endereço por ID
    public Endereco buscarPorId(int idCuidador) {
        Connection con = ConnectionFactory.getConnection();
        EnderecoDAO enderecoDAO = new EnderecoDAO(con);
        Endereco endereco = enderecoDAO.buscarPorId(idCuidador);
        ConnectionFactory.closeConnection();
        return endereco;
    }

    //  Cadastrar Endereço
    public Endereco save(Endereco endereco) {
        Connection con = ConnectionFactory.getConnection();
        EnderecoDAO enderecoDAO = new EnderecoDAO(con);
        Endereco novoEndereco = enderecoDAO.inserir(endereco);
        ConnectionFactory.closeConnection();
        return novoEndereco;
    }

    //  Atualizar Endereço
    public Endereco update(Endereco endereco) {
        Connection con = ConnectionFactory.getConnection();
        EnderecoDAO enderecoDAO = new EnderecoDAO(con);
        Endereco EnderecoAtualizado = enderecoDAO.alterar(endereco);
        ConnectionFactory.closeConnection();
        return EnderecoAtualizado;
    }

    //  Excluir Endereço
    public boolean delete(int idEndereco) {
        Connection con = ConnectionFactory.getConnection();
        EnderecoDAO enderecoDAO = new EnderecoDAO(con);
        boolean resultado = enderecoDAO.excluir(idEndereco);
        ConnectionFactory.closeConnection();
        return resultado;
    }
}
