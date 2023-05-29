package edu.binary_brains.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.binary_brains.entity.Cliente;

public class ClienteRepository {

    private EntityManagerFactory emf;

    public ClienteRepository() {
        emf = Persistence.createEntityManagerFactory("storePersistenceUnit");
    }

    public Cliente obtenerClientePorId(int id) {
        EntityManager em = emf.createEntityManager();
        Cliente cliente = null;

        try {
            cliente = em.find(Cliente.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return cliente;
    }

    public void guardarCliente(Cliente cliente) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void actualizarCliente(Cliente cliente) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void eliminarCliente(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.remove(cliente);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<Cliente> listarClientes() {
        EntityManager em = emf.createEntityManager();
        List<Cliente> clientes = null;

        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT p FROM Cliente p", Cliente.class);
            clientes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        if (clientes == null)
            clientes = new ArrayList<>();

        return clientes;
    }
}
