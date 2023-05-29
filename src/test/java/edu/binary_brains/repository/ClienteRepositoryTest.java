package edu.binary_brains.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.binary_brains.entity.Cliente;

import java.util.List;

public class ClienteRepositoryTest {
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        clienteRepository = new ClienteRepository();
    }

    @Test
    public void testGuardarCliente() {
        // Arrange
        Cliente cliente = new Cliente("Nombre", "Dirección", "Teléfono");

        // Act
        clienteRepository.guardarCliente(cliente);

        // Assert
        Cliente clienteGuardado = clienteRepository.obtenerClientePorId(cliente.getId());
        Assertions.assertNotNull(clienteGuardado);
        Assertions.assertEquals(cliente.getId(), clienteGuardado.getId());

        clienteRepository.eliminarCliente(clienteGuardado.getId());
    }

    @Test
    public void testActualizarCliente() {
        // Arrange
        Cliente cliente = new Cliente("Nombre", "Dirección", "Teléfono");
        clienteRepository.guardarCliente(cliente);

        // Act
        cliente.setNombre("Nuevo Nombre");
        cliente.setDireccion("Nueva Dirección");
        cliente.setTelefono("Nuevo Teléfono");
        clienteRepository.actualizarCliente(cliente);

        // Assert
        Cliente clienteActualizado = clienteRepository.obtenerClientePorId(cliente.getId());
        Assertions.assertNotNull(clienteActualizado);
        Assertions.assertEquals(cliente.getNombre(), clienteActualizado.getNombre());
        Assertions.assertEquals(cliente.getDireccion(), clienteActualizado.getDireccion());
        Assertions.assertEquals(cliente.getTelefono(), clienteActualizado.getTelefono());

        clienteRepository.eliminarCliente(clienteActualizado.getId());
    }

    @Test
    public void testEliminarCliente() {
        // Arrange
        Cliente cliente = new Cliente("Nombre", "Dirección", "Teléfono");
        clienteRepository.guardarCliente(cliente);

        // Act
        clienteRepository.eliminarCliente(cliente.getId());

        // Assert
        Cliente clienteEliminado = clienteRepository.obtenerClientePorId(cliente.getId());
        Assertions.assertNull(clienteEliminado);
    }

    @Test
    public void testListarClientes() {
        // Arrange
        Cliente cliente1 = new Cliente("Nombre1", "Dirección1", "Teléfono1");
        Cliente cliente2 = new Cliente("Nombre2", "Dirección2", "Teléfono2");
        clienteRepository.guardarCliente(cliente1);
        clienteRepository.guardarCliente(cliente2);

        // Act
        List<Cliente> clientes = clienteRepository.listarClientes();

        // Assert
        Assertions.assertEquals(2, clientes.size());
        Assertions.assertTrue(clientes.contains(cliente1));
        Assertions.assertTrue(clientes.contains(cliente2));

        clienteRepository.eliminarCliente(cliente1.getId());
        clienteRepository.eliminarCliente(cliente2.getId());
    }
}
