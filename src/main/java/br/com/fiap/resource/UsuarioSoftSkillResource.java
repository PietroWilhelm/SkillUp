package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioSoftSkillBO;
import br.com.fiap.to.UsuarioSoftSkill;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuario-softskills")
public class UsuarioSoftSkillResource {

    private final UsuarioSoftSkillBO usuarioSoftSkillBO = new UsuarioSoftSkillBO();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos(@Valid UsuarioSoftSkill usuarioSoftSkill) {
        try {
            List<UsuarioSoftSkill> lista = usuarioSoftSkillBO.listarTodos();
            if (lista == null || lista.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhuma relação Usuário-SoftSkill encontrada.")
                        .build();
            }
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("Erro ao listar SoftSkills: " + e.getMessage())
                    .build();
        }
    }


    @GET
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPorUsuario(@Valid @PathParam("idUsuario") int idUsuario) {
        try {
            List<UsuarioSoftSkill> lista = usuarioSoftSkillBO.listarPorUsuario(idUsuario);
            if (lista == null || lista.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Usuário sem SoftSkills vinculadas.")
                        .build();
            }
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("Erro ao listar SoftSkills do usuário: " + e.getMessage())
                    .build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(@Valid UsuarioSoftSkill usuarioSoftSkill) {
        try {
            String resultado = usuarioSoftSkillBO.cadastrarUsuarioSoftSkill(usuarioSoftSkill);
            return Response.status(Response.Status.CREATED).entity(resultado).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("Erro ao cadastrar SoftSkill do usuário: " + e.getMessage())
                    .build();
        }
    }


    @PUT
    @Path("/{idUsuario}/{idSoftSkill}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(@Valid @PathParam("idUsuario") int idUsuario,
                              @PathParam("idSoftSkill") int idSoftSkill,
                              UsuarioSoftSkill body) {
        try {
            UsuarioSoftSkill u = new UsuarioSoftSkill();
            u.setIdUsuario(idUsuario);
            u.setIdSoftSkill(idSoftSkill);
            u.setNivelDominio(body.getNivelDominio());

            String resultado = usuarioSoftSkillBO.atualizarUsuarioSoftSkill(u);
            return Response.ok(resultado).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("Erro ao atualizar SoftSkill do usuário: " + e.getMessage())
                    .build();
        }
    }


    @DELETE
    @Path("/{idUsuario}/{idSoftSkill}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@Valid @PathParam("idUsuario") int idUsuario,
                            @PathParam("idSoftSkill") int idSoftSkill) {
        try {
            UsuarioSoftSkill u = new UsuarioSoftSkill();
            u.setIdUsuario(idUsuario);
            u.setIdSoftSkill(idSoftSkill);

            String resultado = usuarioSoftSkillBO.excluirUsuarioSoftSkill(u);
            return Response.ok(resultado).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("Erro ao excluir SoftSkill do usuário: " + e.getMessage())
                    .build();
        }
    }
}
