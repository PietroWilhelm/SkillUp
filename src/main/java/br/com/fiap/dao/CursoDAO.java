package br.com.fiap.dao;

import br.com.fiap.to.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    // Inserir curso
    public String inserir(Curso curso) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO SU_CURSO (nm_curso, ds_area, ds_nivel, carga_horario) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getArea());
            ps.setString(3, curso.getNivel());
            ps.setInt(4, curso.getCargaHoraria());
            ps.executeUpdate();

            return "Curso cadastrado com sucesso!";
        }
    }

    // Atualizar curso
    public String alterar(Curso curso) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE SU_CURSO SET nm_curso=?, ds_area=?, ds_nivel=?, carga_horario=? WHERE id_curso=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getArea());
            ps.setString(3, curso.getNivel());
            ps.setInt(4, curso.getCargaHoraria());
            ps.setInt(5, curso.getIdCurso());
            ps.executeUpdate();

            return "Curso atualizado com sucesso!";
        }
    }

    // Excluir curso
    public String excluir(int idCurso) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM SU_CURSO WHERE id_curso=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCurso);
            ps.executeUpdate();
            return "Curso excluído com sucesso!";
        }
    }

    // Listar todos os cursos
    public List<Curso> listarTodos() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM SU_CURSO";
        List<Curso> cursos = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNome(rs.getString("nm_curso"));
                curso.setArea(rs.getString("ds_area"));
                curso.setNivel(rs.getString("ds_nivel"));
                curso.setCargaHoraria(rs.getInt("carga_horario"));
                cursos.add(curso);
            }
        }
        return cursos;
    }

    // Buscar um curso por ID
    public Curso listarUm(int idCurso) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM SU_CURSO WHERE id_curso=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCurso);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNome(rs.getString("nm_curso"));
                curso.setArea(rs.getString("ds_area"));
                curso.setNivel(rs.getString("ds_nivel"));
                curso.setCargaHoraria(rs.getInt("carga_horario"));
                return curso;
            }
        }
        return null;
    }
}
