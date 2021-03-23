/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.vista;

import es.uma.informatica.sii.agendaee.entidades.Contacto;
import es.uma.informatica.sii.agendaee.negocio.AgendaException;
import es.uma.informatica.sii.agendaee.negocio.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author francis
 */
@Named(value = "contactos")
@RequestScoped
public class Contactos {

    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };

    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private Contacto contacto;
    private Modo modo;

    public Contactos() {
        contacto = new Contacto();
        modo = Modo.NOACCION;
    }

    public Modo getModo() {
        return modo;
    }

    public void setModo(Modo modo) {
        this.modo = modo;
    }

    public String getAccion() {
        switch (modo) {
            case MODIFICAR:
                return "Modificar";
            case INSERTAR:
                return "Insertar";

        }
        return null;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public String modificar(Contacto c) {
        contacto = c;
        setModo(Modo.MODIFICAR);
        return "edicionContacto.xhtml";
    }

    public String eliminar(Contacto c) {
        try {
            negocio.eliminarContacto(c);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (AgendaException e) {
            return "login.xhtml";
        }
        return null;
    }

    public String insertarContacto() {
        setModo(Modo.INSERTAR);
        return "edicionContacto.xhtml";
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    contacto.setUsuario(sesion.getUsuario());
                    negocio.modificar(contacto);
                    break;
                case INSERTAR:
                    contacto.setUsuario(sesion.getUsuario());
                    negocio.insertar(contacto);
                    break;
            }
            sesion.refrescarUsuario();
            return "contactos.xhtml";
        } catch (AgendaException e) {
            return "login.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }

}
