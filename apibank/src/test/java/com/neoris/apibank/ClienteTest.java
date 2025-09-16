package com.neoris.apibank;

import com.neoris.apibank.entity.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testCrearCliente() {
        Cliente cliente = new Cliente();
        cliente.setClienteId("C001");
        cliente.setContrasena("1234");
        cliente.setEstado(true);

        assertNotNull(cliente);
        assertEquals("C001", cliente.getClienteId());
        assertEquals("1234", cliente.getContrasena());
        assertTrue(cliente.getEstado());
    }
}
