package br.com.fiap.dao;

import br.com.fiap.to.Endereco;
import java.sql.*;
import java.util.ArrayList;

public class EnderecoDAO {

    private Connection con;

    public EnderecoDAO(Connection con) {
        this.con = con;
    }

    //  Listar todos os Endereços
    public ArrayList<Endereco> listarTodos() {
        ArrayList<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM SU_ENDERECO";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(rs.getInt("id_endereco"));
                endereco.setDsLogradouro(rs.getString("ds_logradouro"));
                endereco.setNumero(rs.getInt("nr_numero"));
                endereco.setDsEstado(rs.getString("ds_estado"));
                endereco.setDsCidade(rs.getString("ds_cidade"));
                endereco.setNrCep(rs.getString("nr_cep"));
                endereco.setIdUsuario(rs.getInt("id_usuario"));
                enderecos.add(endereco);
            }

            System.out.println(" Endereços listados com sucesso! Total: " + enderecos.size());

        } catch (SQLException e) {
            System.out.println(" Erro ao listar Endereços: " + e.getMessage());
        }

        return enderecos;
    }

    // Buscar Endereço por ID
    public Endereco buscarPorId(int id) {
        Endereco endereco = null;
        String sql = "SELECT * FROM SU_ENDERECO WHERE ID_ENDERECO = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                endereco = new Endereco();
                endereco.setIdEndereco(rs.getInt("id_endereco"));
                endereco.setDsLogradouro(rs.getString("ds_logradouro"));
                endereco.setNumero(rs.getInt("nr_numero"));
                endereco.setDsCidade(rs.getString("ds_cidade"));
                endereco.setDsEstado(rs.getString("ds_estado"));
                endereco.setNrCep(rs.getString("nr_cep"));
                endereco.setIdUsuario(rs.getInt("id_usuario"));
                System.out.println(" Endereço encontrado com sucesso: " + endereco.getDsLogradouro());
            } else {
                System.out.println(" Nenhum endereço encontrado com o ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println(" Erro ao buscar endereço: " + e.getMessage());
        }

        return endereco;
    }

    // Inserir endereço
    public Endereco inserir(Endereco endereco) {
        String sql = """
            INSERT INTO SU_ENDERECO 
            (DS_LOGRADOURO, NR_NUMERO, DS_CIDADE, DS_ESTADO, NR_CEP, ID_USUARIO)
             VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID_ENDERECO"})) {
            ps.setString(1, endereco.getDsLogradouro());
            ps.setInt(2, endereco.getNumero());
            ps.setString(3, endereco.getDsCidade());
            ps.setString(4, endereco.getDsEstado());
            ps.setString(5, endereco.getNrCep());
            ps.setInt(6, endereco.getIdUsuario());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    endereco.setIdEndereco(rs.getInt(1)); // ✅ agora o Oracle retorna o ID correto
                }
            }

            System.out.println(" Endereço inserido com sucesso: " + endereco.getDsLogradouro());
            return endereco;

        } catch (SQLException e) {
            System.out.println(" Erro ao inserir Endereço: " + e.getMessage());
            return null;
        }
    }


        //  Atualizar Endereço
    public Endereco alterar(Endereco endereco) {
        String sql = "UPDATE SU_ENDERECO SET DS_LOGRADOURO=?, NR_NUMERO=?, DS_CIDADE=?, DS_ESTADO=?, NR_CEP=? , ID_USUARIO=? WHERE ID_ENDERECO=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, endereco.getDsLogradouro());
            ps.setInt(2, endereco.getNumero());
            ps.setString(3, endereco.getDsCidade());
            ps.setString(4, endereco.getDsEstado());
            ps.setString(5, endereco.getNrCep());
            ps.setInt(6, endereco.getIdUsuario());
            ps.setInt(7,endereco.getIdEndereco());

            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println(" Endereço atualizado com sucesso: " + endereco.getDsLogradouro());
                return endereco;
            } else {
                System.out.println(" Nenhum endereço encontrado para atualização (ID: " + endereco.getIdEndereco() + ")");
            }

        } catch (SQLException e) {
            System.out.println(" Erro ao atualizar endereço: " + e.getMessage());
        }

        return null;
    }

    // Excluir endereço
    public boolean excluir(int id) {
        String sql = "DELETE FROM SU_ENDERECO WHERE ID_ENDERECO = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println("Endereço excluído com sucesso (ID: " + id + ")");
                return true;
            } else {
                System.out.println(" Nenhum endereço encontrado para exclusão (ID: " + id + ")");
            }
        } catch (SQLException e) {
            System.out.println(" Erro ao excluir endereço: " + e.getMessage());
        }
        return false;
    }
}
