/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.negocio;

import es.uma.informatica.sii.agendaee.entidades.Contacto;
import es.uma.informatica.sii.agendaee.entidades.Usuario;
import javax.ejb.Local;

/**
 *
 * @author francis
 */
@Local
public interface Negocio {
    public void registrarUsuario(Usuario u)throws AgendaException;
    public void validarCuenta(String cuenta, String validacion) throws AgendaException;
    public void compruebaLogin(Usuario u) throws AgendaException;
    public Usuario refrescarUsuario(Usuario u) throws AgendaException;
    public void modificar(Contacto c) throws AgendaException;
    public void insertar(Contacto c)throws AgendaException;
    public void eliminarContacto(Contacto c)throws AgendaException;
    public Contacto obtenerContacto (Usuario u, Long id)throws AgendaException;
}
