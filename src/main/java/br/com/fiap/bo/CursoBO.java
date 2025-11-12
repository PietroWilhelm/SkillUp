package br.com.fiap.bo;

import br.com.fiap.dao.CursoDAO;
import br.com.fiap.to.Curso;

import java.sql.SQLException;
import java.util.List;

public class CursoBO {

    private final CursoDAO dao = new CursoDAO();

    public String cadastrarCurso(Curso curso) throws SQLException, ClassNotFoundException {
        return dao.inserir(curso);

    }

    public String atualizarCurso(Curso curso) throws SQLException, ClassNotFoundException {
        return dao.alterar(curso);
    }

    public String excluirCurso(int idCurso) throws SQLException, ClassNotFoundException {
        return dao.excluir(idCurso);
    }

    public List<Curso> listarTodosCurso() throws SQLException, ClassNotFoundException {
        return dao.listarTodos();
    }

    public Curso buscarCursoPorId(int idCurso) throws SQLException, ClassNotFoundException {
        return dao.listarUm(idCurso);
    }
}
