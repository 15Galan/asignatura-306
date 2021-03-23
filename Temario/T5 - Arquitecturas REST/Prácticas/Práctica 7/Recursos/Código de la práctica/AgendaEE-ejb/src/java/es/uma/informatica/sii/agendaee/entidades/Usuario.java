/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.informatica.sii.agendaee.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.xml.bind.annotation.*;

/**
 *
 * @author francis
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario implements Serializable {
    
    @XmlTransient
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellidos;
    private String email;
    
    @Id
    private String cuenta;
    private String contrasenia;
    
    private String cadenaValidacion;

    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    @OrderBy ("nombre ASC")
    
    private List<Contacto> contactos;

    
     public String getCadenaValidacion() {
        return cadenaValidacion;
    }

    public void setCadenaValidacion(String cadenaValidacion) {
        this.cadenaValidacion = cadenaValidacion;
    }
    
    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }


    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getCuenta() {
        return cuenta;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.cuenta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.cuenta, other.cuenta)) {
            return false;
        }
        return true;
    }
    
    

    

    @Override
    public String toString() {
        return "es.uma.informatica.sii.agendaee.entidades.Usuario[ cuenta=" + cuenta + " ]";
    }
    
}
