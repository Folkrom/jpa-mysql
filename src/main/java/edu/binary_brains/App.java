/**
 * Testing JPA with MySQL
 * 
 * @author Folkrom
 */

package edu.binary_brains;

import java.util.List;

import javax.persistence.*;

import edu.binary_brains.entity.Users;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Crear un nuevo usuario
        // Users user = new Users("john.doe2", "password123", "Administrador");
        // em.persist(user);

        // Consultar usuarios
        TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u", Users.class);
        List<Users> users = query.getResultList();
        for (Users u : users) {
            System.out.println("Username: " + u.getUsername());
        }

        em.getTransaction().commit();
        em.close();
        emf.close();

    }
}
