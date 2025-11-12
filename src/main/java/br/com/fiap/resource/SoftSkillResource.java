package br.com.fiap.resource;

import br.com.fiap.bo.SoftSkillBO;
import br.com.fiap.to.SoftSkill;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/softskills")
public class SoftSkillResource {

    private final SoftSkillBO softSkillBO = new SoftSkillBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        Response.ResponseBuilder response;
        List<SoftSkill> resultado;

        try {
            resultado = softSkillBO.listarTodasSoftSkills();
            if (resultado != null && !resultado.isEmpty()) {
                response = Response.ok(resultado); // 200 OK
            } else {
                response = Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhuma SoftSkill encontrada.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            response = Response.serverError()
                    .entity("Erro ao buscar SoftSkills: " + e.getMessage());
        }

        return response.build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(SoftSkill softSkill) {
        Response.ResponseBuilder response;

        try {
            String resultado = softSkillBO.inserirSoftSkill(softSkill);
            response = Response.status(Response.Status.CREATED)
                    .entity(resultado); // 201 CREATED
        } catch (Exception e) {
            response = Response.serverError()
                    .entity("Erro ao inserir SoftSkill: " + e.getMessage());
        }

        return response.build();
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, SoftSkill softSkill) {
        Response.ResponseBuilder response;

        try {
            softSkill.setIdSoftSkill(id);
            String resultado = softSkillBO.atualizarSoftSkill(softSkill);
            response = Response.ok(resultado);
        } catch (Exception e) {
            response = Response.serverError()
                    .entity("Erro ao atualizar SoftSkill: " + e.getMessage());
        }

        return response.build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        Response.ResponseBuilder response;
        SoftSkill softSkill = new SoftSkill();
        softSkill.setIdSoftSkill(id);

        try {
            String resultado = softSkillBO.excluirSoftSkillo(softSkill);
            response = Response.ok(resultado);
        } catch (Exception e) {
            response = Response.serverError()
                    .entity("Erro ao excluir SoftSkill: " + e.getMessage());
        }

        return response.build();
    }
}
