package practicatres;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-26T12:12:14")
@StaticMetamodel(Autor.class)
public class Autor_ { 

    public static volatile SingularAttribute<Autor, Date> fechaNacimiento;
    public static volatile SingularAttribute<Autor, String> apellido;
    public static volatile SingularAttribute<Autor, Integer> indiceH;
    public static volatile SingularAttribute<Autor, Long> id;
    public static volatile SetAttribute<Autor, String> firmas;
    public static volatile SingularAttribute<Autor, String> nombre;

}