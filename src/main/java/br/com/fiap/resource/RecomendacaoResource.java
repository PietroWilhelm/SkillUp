package br.com.fiap.resource;

import br.com.fiap.bo.ResultadoBO;
import br.com.fiap.to.Recomendacao;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/recomendacoes")
public class RecomendacaoResource {

    ResultadoBO bo = new ResultadoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Recomendacao resultado) {
        try {
            String resp = bo.inserir(resultado);
            return Response.status(201).entity(resp).build();
        } catch (Exception e) {
            return Response.status(401).entity("Erro ao inserir resultado: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") int id, Recomendacao recomendacao) {
        try {
            recomendacao.setIdRecomendacao(id);
            String resp = bo.atualizar(recomendacao);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return Response.status(500).entity("Erro ao atualizar recomendação: " + e.getMessage()).build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int id) {
        try {
            Recomendacao resultado = new Recomendacao();
            resultado.setIdRecomendacao(id);
            String resp = bo.excluir(resultado);
            return Response.status(200).entity(resp).build();
        } catch (Exception e) {
            return Response.status(500).entity("Erro ao excluir recomendação: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            String resp = bo.buscarPorId(id);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return Response.status(500).entity("Erro ao buscar resultado: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPorPaciente(@PathParam("idUsuario") int idPaciente) {
        try {
            List<Recomendacao> lista = bo.listarPorUsuario(idPaciente);
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(500).entity("Erro ao listar exames: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodas() {
        try {
            List<Recomendacao> lista = bo.listarTodas();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("Erro ao listar recomendações: " + e.getMessage())
                    .build();
        }
    }

}
