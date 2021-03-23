/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.negocio;

import es.uma.informatica.sii.agendaee.entidades.Contacto;
import es.uma.informatica.sii.agendaee.entidades.Usuario;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author francis
 */
@Stateless
public class NegocioImpl implements Negocio {

    private static final int TAM_CADENA_VALIDACION = 20;

    //@Resource(name = "mail/agenda")
    private Session session;

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

        //sendEmail(u, url_validacion);

        System.out.println(url_validacion);
    }

    private void sendEmail(Usuario u, String url) {
        String status;
        try {
            Message message = new MimeMessage(session);
            message.setFrom();
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(u.getEmail(), false));
            message.setSubject("Cadena de validación para la Agenda");
            message.setHeader("X-Mailer", "JavaMail");
            DateFormat dateFormatter = DateFormat
                    .getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
            Date timeStamp = new Date();
            String messageBody = "Pulse el siguiente enlace para validar su cuenta " + url;
            message.setText(messageBody);
            message.setSentDate(timeStamp);
            Transport.send(message);
            status = "Sent";
            //logger.log(Level.INFO, "Mail sent to {0}", u.getEmail());
        } catch (MessagingException ex) {
            //logger.severe("Error in sending message.");
            status = "Encountered an error";
            //logger.severe(ex.getMessage() + ex.getNextException().getMessage());
            //logger.severe(ex.getCause().getMessage());
        }
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

    @Override
    public void compruebaLogin(Usuario u) throws AgendaException {
        Usuario user = em.find(Usuario.class, u.getCuenta());
        if (user == null) {
            throw new CuentaInexistenteException();
        }

        if (user.getCadenaValidacion() != null) {
            throw new CuentaInactivaException();
        }

        if (!user.getContrasenia().equals(u.getContrasenia())) {
            throw new ContraseniaInvalidaException();
        }
    }

    @Override
    public Usuario refrescarUsuario(Usuario u) throws AgendaException {
        compruebaLogin(u);
        Usuario user = em.find(Usuario.class, u.getCuenta());
        em.refresh(user);
        return user;

    }

    @Override
    public void modificar(Contacto c) throws AgendaException{
        Usuario u = c.getUsuario();
        compruebaLogin(u);
        em.merge(c);
    }

    @Override
    public void insertar(Contacto c) throws AgendaException{
        Usuario u = c.getUsuario();
        compruebaLogin(u);
        em.persist(c);
    }

    @Override
    public void eliminarContacto(Contacto c) throws AgendaException{
        Usuario u = c.getUsuario();
        compruebaLogin(u);
        em.remove(em.merge(c));
    }

    @Override
    public Contacto obtenerContacto(Usuario u, Long id) throws AgendaException{
        compruebaLogin(u);
        Contacto c = em.find(Contacto.class, id);
        if (c != null && c.getUsuario().equals(u)) {
            return c;
        }
        // else
        return null;
    }

}
