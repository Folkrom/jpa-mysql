package edu.binary_brains.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.binary_brains.entity.Venta;

public class VentaRepository {

    private EntityManagerFactory emf;

    public VentaRepository() {
        emf = Persistence.createEntityManagerFactory("storePersistenceUnit");
    }

    public Venta obtenerVentaPorId(int id) {
        EntityManager em = emf.createEntityManager();
        Venta venta = null;

        try {
            venta = em.find(Venta.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return venta;
    }

    public void guardarVenta(Venta venta) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(venta);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void actualizarVenta(Venta venta) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(venta);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void eliminarVenta(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Venta venta = em.find(Venta.class, id);
            if (venta != null) {
                em.remove(venta);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<Venta> listarVentas() {
        EntityManager em = emf.createEntityManager();
        List<Venta> ventas = null;

        try {
            TypedQuery<Venta> query = em.createQuery("SELECT p FROM Venta p", Venta.class);
            ventas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        if (ventas == null)
            ventas = new ArrayList<>();

        return ventas;
    }
}
