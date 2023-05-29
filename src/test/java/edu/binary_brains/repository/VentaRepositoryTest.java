package edu.binary_brains.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.binary_brains.entity.Cliente;
import edu.binary_brains.entity.Producto;
import edu.binary_brains.entity.Venta;

public class VentaRepositoryTest {
    private VentaRepository ventaRepository;
    private ClienteRepository clienteRepository;
    private ProductoRepository productoRepository;

    @BeforeEach
    public void setUp() {
        // Inicializar los repositorios o crear instancias necesarias
        ventaRepository = new VentaRepository();
        clienteRepository = new ClienteRepository();
        productoRepository = new ProductoRepository();
    }

    @Test
    public void testGuardarVenta() {
        // Arrange
        Cliente cliente = new Cliente("Juan", "Calle 123", "555-1234");
        clienteRepository.guardarCliente(cliente);

        Producto producto = new Producto("Producto 1", "Descripción", 10.99, 5);
        productoRepository.guardarProducto(producto);

        Venta venta = new Venta(cliente, producto, 2, null);

        // Act
        ventaRepository.guardarVenta(venta);

        // Assert
        Venta ventaObtenida = ventaRepository.obtenerVentaPorId(venta.getId());
        Assertions.assertNotNull(ventaObtenida);
        Assertions.assertEquals(venta.getId(), ventaObtenida.getId());

        ventaRepository.eliminarVenta(venta.getId());
        clienteRepository.eliminarCliente(cliente.getId());
        productoRepository.eliminarProducto(producto);
    }

    @Test
    public void testActualizarVenta() {
        // Arrange
        Cliente cliente = new Cliente("Juan", "Calle 123", "555-1234");
        clienteRepository.guardarCliente(cliente);

        Producto producto = new Producto("Producto 1", "Descripción", 10.99, 5);
        productoRepository.guardarProducto(producto);

        Venta venta = new Venta(cliente, producto, 2, null);
        ventaRepository.guardarVenta(venta);

        // Act
        venta.setCantidad(3);
        ventaRepository.actualizarVenta(venta);

        // Assert
        Venta ventaActualizada = ventaRepository.obtenerVentaPorId(venta.getId());
        Assertions.assertEquals(3, ventaActualizada.getCantidad());

        ventaRepository.eliminarVenta(ventaActualizada.getId());
        clienteRepository.eliminarCliente(cliente.getId());
        productoRepository.eliminarProducto(producto);
    }

    @Test
    public void testEliminarVenta() {
        // Arrange
        Cliente cliente = new Cliente("Juan", "Calle 123", "555-1234");
        clienteRepository.guardarCliente(cliente);

        Producto producto = new Producto("Producto 1", "Descripción", 10.99, 5);
        productoRepository.guardarProducto(producto);

        Venta venta = new Venta(cliente, producto, 2, null);
        ventaRepository.guardarVenta(venta);

        // Act
        ventaRepository.eliminarVenta(venta.getId());

        // Assert
        Venta ventaEliminada = ventaRepository.obtenerVentaPorId(venta.getId());
        Assertions.assertNull(ventaEliminada);

        clienteRepository.eliminarCliente(cliente.getId());
        productoRepository.eliminarProducto(producto);
    }

    @Test
    public void testObtenerVentaPorId() {
        // Arrange
        Cliente cliente = new Cliente("Juan", "Calle 123", "555-1234");
        clienteRepository.guardarCliente(cliente);

        Producto producto = new Producto("Producto 1", "Descripción", 10.99, 5);
        productoRepository.guardarProducto(producto);

        Venta venta = new Venta(cliente, producto, 2, null);
        ventaRepository.guardarVenta(venta);

        // Act
        Venta ventaObtenida = ventaRepository.obtenerVentaPorId(venta.getId());

        // Assert
        Assertions.assertNotNull(ventaObtenida);
        Assertions.assertEquals(venta.getId(), ventaObtenida.getId());

        ventaRepository.eliminarVenta(ventaObtenida.getId());
        clienteRepository.eliminarCliente(cliente.getId());
        productoRepository.eliminarProducto(producto);

    }

    @Test
    public void testListarVentas() {
        // Arrange
        Cliente cliente1 = new Cliente("Juan", "Calle 123", "555-1234");
        clienteRepository.guardarCliente(cliente1);

        Cliente cliente2 = new Cliente("María", "Avenida 456", "555-5678");
        clienteRepository.guardarCliente(cliente2);

        Producto producto1 = new Producto("Producto 1", "Descripción", 10.99, 5);
        productoRepository.guardarProducto(producto1);

        Producto producto2 = new Producto("Producto 2", "Descripción", 19.99, 10);
        productoRepository.guardarProducto(producto2);

        Venta venta1 = new Venta(cliente1, producto1, 2, null);
        ventaRepository.guardarVenta(venta1);

        Venta venta2 = new Venta(cliente2, producto2, 1, null);
        ventaRepository.guardarVenta(venta2);

        // Act
        List<Venta> ventas = ventaRepository.listarVentas();

        // Assert
        Assertions.assertEquals(2, ventas.size());
        Assertions.assertTrue(ventas.contains(venta1));
        Assertions.assertTrue(ventas.contains(venta2));

        ventaRepository.eliminarVenta(venta1.getId());
        ventaRepository.eliminarVenta(venta2.getId());

        ventaRepository.eliminarVenta(venta1.getId());
        ventaRepository.eliminarVenta(venta2.getId());
        clienteRepository.eliminarCliente(cliente1.getId());
        clienteRepository.eliminarCliente(cliente2.getId());
        productoRepository.eliminarProducto(producto1);
        productoRepository.eliminarProducto(producto2);
    }

}
