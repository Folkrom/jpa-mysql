package edu.binary_brains.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.binary_brains.entity.Producto;

public class ProductoRepository {

    private EntityManagerFactory emf;

    public ProductoRepository() {
        emf = Persistence.createEntityManagerFactory("storePersistenceUnit");
    }

    public void close() {
        emf.close();
    }

    public void guardarProducto(Producto producto) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(producto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Producto obtenerProductoPorId(int id) {
        EntityManager em = emf.createEntityManager();
        Producto producto = null;

        try {
            producto = em.find(Producto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return producto;
    }

    public void actualizarProducto(Producto producto) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(producto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminarProducto(Producto producto) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.remove(em.merge(producto));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Producto> listarProductos() {
        EntityManager em = emf.createEntityManager();
        List<Producto> productos = null;

        try {
            TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
            productos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        if (productos == null)
            productos = new ArrayList<>();

        return productos;
    }
}
