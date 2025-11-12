package br.com.fiap.resource;
import br.com.fiap.bo.CursoBO;
import br.com.fiap.to.Curso;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cursos")
public class CursoResource {

    private final CursoBO cursoBo = new CursoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Curso> resultado = null;
        Response.ResponseBuilder response;

        try {
            resultado = cursoBo.listarTodosCurso();
            if (resultado != null && !resultado.isEmpty()) {
                response = Response.ok(); // 200 - OK
            } else {
                response = Response.status(404); // 404 - NOT FOUND
            }
        } catch (Exception e) {
            response = Response.serverError().entity(e.getMessage());
        }

        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        Curso resultado = null;
        Response.ResponseBuilder response;

        try {
            resultado = cursoBo.buscarCursoPorId(id);
            if (resultado != null) {
                response = Response.ok(); // 200 - OK
            } else {
                response = Response.status(404); // 404 - NOT FOUND
            }
        } catch (Exception e) {
            response = Response.serverError().entity(e.getMessage());
        }

        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Curso Curso) {
        String resultado;
        Response.ResponseBuilder response;

        try {
            resultado = cursoBo.cadastrarCurso(Curso);
            response = Response.created(null); // 201 - CREATED
            response.entity(resultado);
        } catch (Exception e) {
            response = Response.status(400).entity(e.getMessage()); // 400 - BAD REQUEST
        }

        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Curso curso, @PathParam("id") int id) {
        curso.setIdCurso(id);
        String resultado;
        Response.ResponseBuilder response;

        try {
            resultado = cursoBo.atualizarCurso(curso);
            response = Response.ok(); // 200 - OK
            response.entity(resultado);
        } catch (Exception e) {
            response = Response.status(400).entity(e.getMessage());
        }

        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Response.ResponseBuilder response;

        try {
            String resultado = cursoBo.excluirCurso(id);
            if (resultado != null) {
                response = Response.status(204).entity("Curso cancelado com Sucesso"); // 204 - NO CONTENT
            } else {
                response = Response.status(404); // 404 - NOT FOUND
            }
        } catch (Exception e) {
            response = Response.serverError().entity(e.getMessage());
        }

        return response.build();
    }
}
