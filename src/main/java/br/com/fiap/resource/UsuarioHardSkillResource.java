package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioHardSkillBO;

import br.com.fiap.to.HardSkill;
import br.com.fiap.to.UsuarioHardSkill;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/usuario-hardskill")
public class UsuarioHardSkillResource {

    UsuarioHardSkillBO usuarioHardSkillBO = new UsuarioHardSkillBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@Valid UsuarioHardSkill usuarioHardSkill) {
        Response.ResponseBuilder response;
        List<UsuarioHardSkill> resultado;
        try {
            resultado = usuarioHardSkillBO.listarTodosHardskils();
            if (resultado != null) {
                response = Response.ok(resultado); // 200 OK
            } else {
                response = Response.status(404).entity("Nenhuma Hardskill encontrada aos usuários"); // 404 Not Found
            }

        } catch (ClassNotFoundException | SQLException e) {
            response = Response.serverError().entity("Erro ao buscar Hardskills: " + e.getMessage()); // 500
        }

        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid UsuarioHardSkill usuarioHardSkill) {
        Response.ResponseBuilder response;
        String resultado;

        try {
            resultado = usuarioHardSkillBO.cadastrarUsuarioHardSkill(usuarioHardSkill);
            response = Response.status(Response.Status.CREATED).entity(resultado); // 201 CREATED
        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao inserir HardSkill: " + e.getMessage());
        }

        return response.build();
    }

    @PUT
    @Path("/{idUsuario}/{idHardSkill}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(@Valid @PathParam("idUsuario") int idUsuario,
                              @PathParam("idHardSkill") int idHardSkill,
                              UsuarioHardSkill body) {
        try {
            UsuarioHardSkill uhs = new UsuarioHardSkill();
            uhs.setIdUsuario(idUsuario);
            uhs.setIdHardSkill(idHardSkill);
            uhs.setNivelConhecimento(body.getNivelConhecimento());
            String resultado = usuarioHardSkillBO.atualizarUsuarioHardSkill(uhs);
            return Response.ok(resultado).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("Erro ao atualizar HardSkill do usuário: " + e.getMessage())
                    .build();
        }
    }


    @DELETE
    @Path("/{idUsuario}/{idHardSkill}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@Valid @PathParam("idUsuario") int idUsuario,
                            @PathParam("idHardSkill") int idHardSkill) {
        try {
            UsuarioHardSkill uhs = new UsuarioHardSkill();
            uhs.setIdUsuario(idUsuario);
            uhs.setIdHardSkill(idHardSkill);

            String resultado = usuarioHardSkillBO.excluirUsuarioHardSkill(uhs);
            return Response.ok(resultado).build();

        } catch (Exception e) {
            return Response.serverError()
                    .entity("Erro ao excluir HardSkill do usuário: " + e.getMessage())
                    .build();
        }
    }

}
