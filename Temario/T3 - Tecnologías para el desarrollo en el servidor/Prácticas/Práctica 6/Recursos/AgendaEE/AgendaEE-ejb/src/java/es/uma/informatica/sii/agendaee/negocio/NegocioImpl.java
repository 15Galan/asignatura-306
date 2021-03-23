/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.negocio;

import es.uma.informatica.sii.agendaee.entidades.Contacto;
import es.uma.informatica.sii.agendaee.entidades.Usuario;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author francis
 */
@Stateless
public class NegocioImpl implements Negocio {

    private static final int TAM_CADENA_VALIDACION = 20;

    @PersistenceContext(unitName = "AgendaEE-EntidadesPU")
    private EntityManager em;

    @Override
    public void registrarUsuario(Usuario u) throws AgendaException {
        Usuario user = em.find(Usuario.class, u.getCuenta());
        if (user != null) {
            // El usuario ya existe
            throw new CuentaRepetidaException();
        }

        u.setCadenaValidacion(generarCadenaAleatoria());
        em.persist(u);

        String url_validacion = "http://localhost:8080/AgendaEE-war/faces/validarCuenta.xhtml?cuenta="
                + u.getCuenta() + "&codigoValidacion=" + u.getCadenaValidacion();

        System.out.println(url_validacion);
    }

    private String generarCadenaAleatoria() {
        Random rnd = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < TAM_CADENA_VALIDACION; i++) {
            int v = rnd.nextInt(62);
            if (v < 26) {
                sb.append((char) ('a' + v));
            } else if (v < 52) {
                sb.append((char) ('A' + v - 26));
            } else {
                sb.append((char) ('0' + v - 52));
            }
        }

        return sb.toString();

    }

    @Override
    public void validarCuenta(String cuenta, String validacion) throws AgendaException{
        Usuario u = em.find(Usuario.class, cuenta);
        if (u == null) {
            throw new CuentaInexistenteException();
        }

        if (u.getCadenaValidacion() == null) {
            // la cuenta ya está activa
            return;
        }

        if (!u.getCadenaValidacion().equals(validacion)) {
            throw new ValidacionIncorrectaException();
        }
        // else
        // Eliminamos la cadena de validación, indicando que ya está activa la cuenta
        u.setCadenaValidacion(null);
    }

    /**
     * Este método debe comprobar que el nombre de usuario y contraseña que
     * recibe en el objeto u pertenecen a un usuario que existe en la BBDD y que
     * está validado (un usuario está validado cuando su cadena de validación es
     * nula).
     * 
     * Puede lanzar las excepciones CuentaInexistenteException, CuentaInactivaException
     * y ContraseniaInvalidaException
     *
     * @param u
     * @return
     */
    @Override
    public void compruebaLogin(Usuario u)  throws AgendaException {
        // TODO

    }

    /**
     * Este método debe comprobar que el usuario que se le pasa como parámetro
     * es un usuario existente y con contraseña correcta (ya que estamos en la capa
     * de negocio con un Session Bean de tipo @Stateless, debemos comprobar
     * todos los accesos a la capa de nogocio). En caso negativo debe devolver debe devolver 
     * la excepción que corresponda,
     * en caso afirmativo debe devolver una entidad usuario tal con la información
     * existe ahora mismo en la BBDD.
     * @param u
     * @return 
     */
    @Override
    public Usuario refrescarUsuario(Usuario u) throws AgendaException {
        // TODO
        return null;

    }

    /**
     * Este método debe actualizar el contacto correspondiente en la BBDD con
     * la información contenida en el objeto que se le pasa como argumento.
     * Antes de eso, debe comprobar que el usuario a quien pertenece el contacto existe y 
     * tiene una contraseña correcta (en caso contrario debe devolver la excepción que
     * corresponda.
     * @param c
     * @return 
     */
    @Override
    public void modificar(Contacto c)  throws AgendaException{
        // TODO
    }

    /** Este método debe insertar un contacto en la BBDD. Antes debe comprobar que
     * el usuario del contacto existe y tiene contraseña correcta (en caso contrario
     * debe lanzar la excepción correspodiente).
     * @param c
     * @return 
     */
    
    @Override
    public void insertar(Contacto c) throws AgendaException{
        // TODO
    }

    /**
     * Este método debe eliminar el contacto que se le pasa como argumento.
     * Antes debe comprobar que el usuario a quien pertenece el contacto existe
     * y tiene contraseña válida (en caso contrario debe lanzar la excepción
     * correspondiente).
     * @param c
     * @return 
     */
    @Override
    public void eliminarContacto(Contacto c) throws AgendaException{
        // TODO
    }

}
