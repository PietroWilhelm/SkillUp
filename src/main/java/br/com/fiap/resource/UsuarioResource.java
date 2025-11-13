package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.Usuario;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/usuarios")
public class UsuarioResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<Usuario> resultado = usuarioBO.listarTodos();
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok(); // 200 - OK
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        Usuario resultado = usuarioBO.buscarUsuarioPorId(id);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok(); // 200 - OK
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid Usuario user) {
        try {
            Usuario resultado = usuarioBO.cadastrarUsuario(user);
            if (resultado != null) {
                return Response.status(Response.Status.CREATED)
                        .entity(resultado)
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Erro ao cadastrar Usuário")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao processar cadastro: " + e.getMessage())
                    .build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@Valid @PathParam("id") int id) {
        boolean sucesso = usuarioBO.excluirUsuario(id);
        Response.ResponseBuilder response = null;
        if (sucesso) {
            response = Response.status(204).entity("Usuário Excluido com sucesso!"); // 204 - NO CONTENT
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid Usuario user, @PathParam("id") int id) {
        user.setIdUsuario(id);
        Usuario resultado = usuarioBO.atualizarUsuario(user);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }
}
