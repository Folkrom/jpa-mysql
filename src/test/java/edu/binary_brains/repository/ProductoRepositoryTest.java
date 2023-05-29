package edu.binary_brains.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.binary_brains.entity.Producto;

public class ProductoRepositoryTest {

    @Test
    public void testGuardarProducto() {
        // Arrange
        ProductoRepository productoRepository = new ProductoRepository();
        Producto producto = new Producto("Producto 1", "Descripción del producto", 10.0, 100);

        // Act
        productoRepository.guardarProducto(producto);

        // Assert
        Producto productoGuardado = productoRepository.obtenerProductoPorId(producto.getId());
        Assertions.assertNotNull(productoGuardado);
        Assertions.assertEquals(producto.getNombre(), productoGuardado.getNombre());

        productoRepository.eliminarProducto(productoGuardado);
    }

    @Test
    public void testActualizarProducto() {
        // Arrange
        ProductoRepository productoRepository = new ProductoRepository();
        Producto producto = new Producto("Producto 1", "Descripción del producto", 10.0, 100);
        productoRepository.guardarProducto(producto);

        // Act
        producto.setNombre("Nuevo Nombre");
        producto.setPrecio(15.0);
        productoRepository.actualizarProducto(producto);

        // Assert
        Producto productoActualizado = productoRepository.obtenerProductoPorId(producto.getId());
        Assertions.assertEquals(producto.getNombre(), productoActualizado.getNombre());
        Assertions.assertEquals(producto.getPrecio(), productoActualizado.getPrecio());

        productoRepository.eliminarProducto(productoActualizado);
    }

    @Test
    public void testEliminarProducto() {
        // Arrange
        ProductoRepository productoRepository = new ProductoRepository();
        Producto producto = new Producto("Producto 1", "Descripción del producto", 10.0, 100);
        productoRepository.guardarProducto(producto);

        // Act
        productoRepository.eliminarProducto(producto);

        // Assert
        Producto productoEliminado = productoRepository.obtenerProductoPorId(producto.getId());
        Assertions.assertNull(productoEliminado);
    }

    @Test
    public void testListarProductos() {
        // Arrange
        ProductoRepository productoRepository = new ProductoRepository();
        Producto producto1 = new Producto("Producto 1", "Descripción del producto 1", 10.0, 100);
        Producto producto2 = new Producto("Producto 2", "Descripción del producto 2", 15.0, 50);
        productoRepository.guardarProducto(producto1);
        productoRepository.guardarProducto(producto2);

        // Act
        List<Producto> productos = productoRepository.listarProductos();

        // Assert
        Assertions.assertEquals(2, productos.size());
        Assertions.assertTrue(productos.contains(producto1));
        Assertions.assertTrue(productos.contains(producto2));
        
        productoRepository.eliminarProducto(producto1);
        productoRepository.eliminarProducto(producto2);
    }
}
