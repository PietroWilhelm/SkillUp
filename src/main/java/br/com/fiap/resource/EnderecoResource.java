package br.com.fiap.resource;

import br.com.fiap.bo.EnderecoBO;
import br.com.fiap.to.Endereco;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/enderecos")
public class EnderecoResource {

    private EnderecoBO enderecoBO = new EnderecoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@Valid EnderecoBO enderecoBO) {
        ArrayList<Endereco> resultado = enderecoBO.listarTodos();
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
    public Response findById(@Valid @PathParam("id") int id ) {
        Endereco resultado = enderecoBO.buscarPorId(id);
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
    public Response save(@Valid Endereco endereco) {
        Endereco resultado = enderecoBO.save(endereco);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@Valid @PathParam("id") int id) {
        boolean sucesso = enderecoBO.delete(id);
        Response.ResponseBuilder response = null;
        if (sucesso) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid Endereco endereco, @PathParam("id") int id) {
        endereco.setIdEndereco(id);
        Endereco resultado = enderecoBO.update(endereco);
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
