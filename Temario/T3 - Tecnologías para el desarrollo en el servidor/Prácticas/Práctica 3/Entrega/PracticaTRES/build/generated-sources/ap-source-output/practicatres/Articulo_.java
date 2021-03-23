package practicatres;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import practicatres.Autor;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-26T12:12:14")
@StaticMetamodel(Articulo.class)
public class Articulo_ { 

    public static volatile SingularAttribute<Articulo, Integer> numero;
    public static volatile SingularAttribute<Articulo, Integer> paginaFinal;
    public static volatile SingularAttribute<Articulo, Integer> volumen;
    public static volatile SingularAttribute<Articulo, String> titulo;
    public static volatile SingularAttribute<Articulo, Long> id;
    public static volatile ListAttribute<Articulo, Autor> autores;
    public static volatile SingularAttribute<Articulo, String> publicacion;
    public static volatile SingularAttribute<Articulo, Integer> paginaInicial;
    public static volatile SingularAttribute<Articulo, Integer> anio;

}