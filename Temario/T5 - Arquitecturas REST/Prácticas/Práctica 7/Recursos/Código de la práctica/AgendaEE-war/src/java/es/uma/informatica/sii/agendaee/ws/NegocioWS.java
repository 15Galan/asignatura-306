/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.informatica.sii.agendaee.ws;

import es.uma.informatica.sii.agendaee.entidades.Contacto;
import es.uma.informatica.sii.agendaee.entidades.Usuario;
import es.uma.informatica.sii.agendaee.negocio.AgendaException;
import es.uma.informatica.sii.agendaee.negocio.ContraseniaInvalidaException;
import es.uma.informatica.sii.agendaee.negocio.CuentaInactivaException;
import es.uma.informatica.sii.agendaee.negocio.CuentaInexistenteException;
import es.uma.informatica.sii.agendaee.negocio.Negocio;
import java.net.URI;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author francis
 */
@Path("agenda")
@RequestScoped
public class NegocioWS {

    @Context
    private UriInfo context;
    @EJB
    private Negocio neg;

    /**
     * Creates a new instance of NegocioWS
     */
    public NegocioWS() {
    }

    @GET
    @Path("contactos")
    @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getContactos(@HeaderParam("User-auth") String auth)
    {
        Usuario u = leeAutorizacion(auth);
        
        if (u == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        // else
        try {
            Usuario user = neg.refrescarUsuario(u);
            return Response.ok(user).build();
        } catch (AgendaException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    @POST
    @Path("contactos")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public Response postContacto(@HeaderParam("User-auth") String auth, Contacto c)
    {
        //TODO
        return null;
    }
    
    
    @GET
    @Path("contacto/{id: [0-9]+}")
    @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getContacto(@HeaderParam("User-auth") String auth, @PathParam("id") Long id) {
        // TODO
        return null;
    }
    
    @PUT
    @Path("contacto/{id: [0-9]+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public Response putContacto(@HeaderParam("User-auth") String auth, @PathParam("id") Long id, Contacto c)
    {
        // TODO
        return null;
    }
    
    @DELETE
    @Path("contacto/{id: [0-9]+}")
    public Response deleteContacto(@HeaderParam("User-auth") String auth, @PathParam("id") Long id) {
        // TODO
        return null;
    }
    
    
    private Usuario leeAutorizacion(String auth)
    {
        if (auth == null)
        {
            return null;
        }
        // else
        
        String [] sp = auth.split(":");
        
        if (sp.length < 2)
        {
            return null;
        }

        String id = sp[0];
        String pass = sp[1];
                
        Usuario u = new Usuario();
        u.setCuenta(id);
        u.setContrasenia(pass);
        
        return u;
    }


}
