package br.com.fiap.dao;

import br.com.fiap.to.Usuario;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioDAO {

    private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    // Listar todos os pacientes
    public ArrayList<Usuario> listarTodos() {
        ArrayList<Usuario> users = new ArrayList<>();
        String sql = "SELECT * FROM SU_USUARIO";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setNome(rs.getString("nm_usuario"));
                user.setEmail(rs.getString("ds_email"));
                user.setDtCadastro(rs.getDate("dt_cadastro").toLocalDate());
                user.setSenha(rs.getString("ds_senha"));
                user.setAreaInteresse(rs.getString("ds_area_interesse"));

                users.add(user);
            }

            System.out.println("Usuários listados com sucesso! Total: " + users.size());

        } catch (SQLException e) {
            System.out.println("Erro ao listar Usuários: " + e.getMessage());
        }

        return users;
    }

    // Buscar paciente por ID
    public Usuario buscarPorId(int id) {
        Usuario user = null;
        String sql = "SELECT * FROM SU_USUARIO WHERE id_usuario = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setNome(rs.getString("nm_usuario"));
                user.setEmail(rs.getString("ds_email"));
                user.setAreaInteresse(rs.getString("ds_area_interesse"));
                user.setSenha(rs.getString("ds_senha"));
                user.setDtCadastro(rs.getDate("dt_cadastro").toLocalDate());

                System.out.println(" Usuário encontrado com sucesso: " + user.getNome());
            } else {
                System.out.println(" Nenhum Usuário encontrado com o ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println(" Erro ao buscar Usuário: " + e.getMessage());
        }

        return user;
    }

    //Inserir
    public Usuario inserir(Usuario user) {
        String sql = "INSERT INTO SU_USUARIO (nm_usuario, ds_email, ds_area_interesse, ds_senha, dt_cadastro) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID_USUARIO"})) {
            ps.setString(1, user.getNome());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getAreaInteresse());
            ps.setString(4, user.getSenha());
            ps.setDate(5, Date.valueOf(user.getDtCadastro()));
            ps.executeUpdate();

            // Recuperar o ID gerado automaticamente
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setIdUsuario(rs.getInt(1));
                }
            }

            System.out.println(" Usuário cadastrado com sucesso: " + user.getNome());
            return user;

        } catch (SQLException e) {
            System.out.println(" Erro ao inserir Usuário: " + e.getMessage());
            return null;
        }
    }

    // Atualizar paciente
    public Usuario alterar(Usuario user) {
        String sql = "UPDATE SU_USUARIO SET nm_usuario=?, ds_email=?, ds_area_interesse=?, ds_senha=? WHERE id_usuario=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getNome());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getAreaInteresse());
            ps.setString(4, user.getSenha());
            ps.setInt(5, user.getIdUsuario());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Usuário atualizado com sucesso: " + user.getNome());

                // Buscar novamente o usuário atualizado no banco
                String selectSql = "SELECT id_usuario, nm_usuario, ds_email, ds_area_interesse, ds_senha, dt_cadastro FROM SU_USUARIO WHERE id_usuario=?";
                try (PreparedStatement ps2 = con.prepareStatement(selectSql)) {
                    ps2.setInt(1, user.getIdUsuario());
                    ResultSet rs = ps2.executeQuery();
                    if (rs.next()) {
                        Usuario atualizado = new Usuario();
                        atualizado.setIdUsuario(rs.getInt("id_usuario"));
                        atualizado.setNome(rs.getString("nm_usuario"));
                        atualizado.setEmail(rs.getString("ds_email"));
                        atualizado.setAreaInteresse(rs.getString("ds_area_interesse"));
                        atualizado.setSenha(rs.getString("ds_senha"));
                        atualizado.setDtCadastro(rs.getDate("dt_cadastro").toLocalDate()); // assim o dt cadastro é visivel no put mesmo não sendo modificado
                        return atualizado;
                    }
                }
            } else {
                System.out.println("Nenhum Usuário encontrado para atualização (ID: " + user.getIdUsuario() + ")");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Usuário: " + e.getMessage());
        }
        return null;
    }


    //Excluir paciente
    public boolean excluir(int id) {
        String sql = "DELETE FROM SU_USUARIO WHERE id_usuario=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println(" Usuário excluído com sucesso (ID: " + id + ")");
                return true;
            } else {
                System.out.println(" Nenhum Usuário encontrado para exclusão (ID: " + id + ")");
            }
        } catch (SQLException e) {
            System.out.println(" Erro ao excluir Usuário: " + e.getMessage());
        }
        return false;
    }

}
