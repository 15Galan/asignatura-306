package practicatres;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Jpa {
    
    public static void main (String[] args) {
        Autor autor = new Autor();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PracticaTRESPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        autor.setNombre("Antonio");
        em.persist(autor);
        tx.commit();
        
        em.close();
        emf.close();
    }
}
