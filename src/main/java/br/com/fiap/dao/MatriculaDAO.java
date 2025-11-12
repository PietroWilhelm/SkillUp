package br.com.fiap.dao;

import br.com.fiap.to.Matricula;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {
    private Connection con;

    public MatriculaDAO(Connection con) {
        this.con = con;
    }

    public void inserir(Matricula matricula) throws SQLException {
        String sql = "INSERT INTO SU_MATRICULA (NR_PROGRESSO, DT_INICIO, ID_USUARIO, ID_CURSO) VALUES (?, ?, ?, ?)";

        if (matricula.getDataInicio() == null) {
            matricula.setDataInicio(LocalDate.now());
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, matricula.getNumeroProgresso());
            ps.setDate(2, Date.valueOf(matricula.getDataInicio()));
            ps.setInt(3, matricula.getIdUsuario());
            ps.setInt(4, matricula.getIdCurso());
            ps.executeUpdate();
        }
    }


    public List<Matricula> listarTodos() throws SQLException {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT * FROM SU_MATRICULA ORDER BY ID_MATRICULA";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdMatricula(rs.getInt("ID_MATRICULA"));
                matricula.setNumeroProgresso(rs.getString("NR_PROGRESSO"));
                matricula.setDataInicio(rs.getDate("DT_INICIO").toLocalDate());
                matricula.setIdUsuario(rs.getInt("ID_USUARIO"));
                matricula.setIdCurso(rs.getInt("ID_CURSO"));
                matriculas.add(matricula);
            }
        }
        return matriculas;
    }


    public void atualizar(Matricula matricula) throws SQLException {
        String sql = "UPDATE SU_MATRICULA SET NR_PROGRESSO = ?, DT_INICIO = ?, ID_USUARIO = ?, ID_CURSO = ? WHERE ID_MATRICULA = ?";

        if (matricula.getDataInicio() == null) {
            matricula.setDataInicio(LocalDate.now());
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, matricula.getNumeroProgresso());
            ps.setDate(2, Date.valueOf(matricula.getDataInicio()));
            ps.setInt(3, matricula.getIdUsuario());
            ps.setInt(4, matricula.getIdCurso());
            ps.setInt(5, matricula.getIdMatricula());

            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println("Matrícula atualizada com sucesso! ID: " + matricula.getIdMatricula());
            } else {
                System.out.println("Nenhuma matrícula encontrada para o ID: " + matricula.getIdMatricula());
            }
        }
    }

    public void excluir(int idMatricula) throws SQLException {
        String sql = "DELETE FROM SU_MATRICULA WHERE ID_MATRICULA = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMatricula);

            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println("Matrícula excluída com sucesso! ID: " + idMatricula);
            } else {
                System.out.println("Nenhuma matrícula encontrada para o ID: " + idMatricula);
            }
        }
    }

}
