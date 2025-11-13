package br.com.fiap.resource;

import br.com.fiap.bo.MatriculaBO;

import br.com.fiap.to.Matricula;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/matriculas")
public class MatriculaResource {

    private MatriculaBO bo = new MatriculaBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(@Valid Matricula matricula) {
        try {
            bo.inserir(matricula);
            return Response.status(Response.Status.CREATED).build(); // 201
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao cadastrar matricula: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        try {
            List<Matricula> lista = bo.listarTodos();
            if (lista == null || lista.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Nenhuma matricula encontrado").build();
            }
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao listar matriculas: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid @PathParam("id") int id, Matricula matricula) {
        MatriculaBO bo = new MatriculaBO();
        Response.ResponseBuilder response;
        try {
            matricula.setIdMatricula(id);
            bo.atualizar(matricula); // agora sem retorno
            response = Response.ok("Matrícula atualizada com sucesso!");
        } catch (Exception e) {
            response = Response.serverError()
                    .entity("Erro ao atualizar matrícula: " + e.getMessage());
        }
        return response.build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@Valid @PathParam("id") int id) {
        Response.ResponseBuilder response;
        MatriculaBO bo = new MatriculaBO();
        try {
            bo.excluir(id);
            response = Response.ok("Matrícula excluída com sucesso!");
        } catch (Exception e) {
            response = Response.serverError()
                    .entity("Erro ao excluir matrícula: " + e.getMessage());
        }
        return response.build();
    }


}
