package br.com.fiap.resource;

import br.com.fiap.bo.HardSkillBO;
import br.com.fiap.to.HardSkill;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/hardskills")
public class HardSkillResource {

    private final HardSkillBO hardSkillBO = new HardSkillBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        Response.ResponseBuilder response;
        List<HardSkill> lista;

        try {
            lista = hardSkillBO.listarTodasHardSkills();
            if (lista != null && !lista.isEmpty()) {
                response = Response.ok(lista); // 200 OK
            } else {
                response = Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhuma HardSkill encontrada.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            response = Response.serverError()
                    .entity("Erro ao buscar HardSkills: " + e.getMessage());
        }

        return response.build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HardSkill hardSkill) {
        Response.ResponseBuilder response;
        String resultado;

        try {
            resultado = hardSkillBO.inserir(hardSkill);
            response = Response.status(Response.Status.CREATED).entity(resultado); // 201 CREATED
        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao inserir HardSkill: " + e.getMessage());
        }

        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, HardSkill hardSkill) {
        Response.ResponseBuilder response;
        String resultado;

        try {
            hardSkill.setIdHardSkill(id);
            resultado = hardSkillBO.atualizar(hardSkill);
            response = Response.ok(resultado); // 200 OK
        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao atualizar HardSkill: " + e.getMessage());
        }

        return response.build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        Response.ResponseBuilder response;
        HardSkill hardSkill = new HardSkill();
        hardSkill.setIdHardSkill(id);

        try {
            String resultado = hardSkillBO.excluir(hardSkill);
            response = Response.ok(resultado); // 200 OK
        } catch (Exception e) {
            response = Response.serverError()
                    .entity("Erro ao excluir HardSkill: " + e.getMessage());
        }

        return response.build();
    }
}
