package com.neoris.apibank;

import com.neoris.apibank.entity.Cliente;
import com.neoris.apibank.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void testGuardarYLeerCliente() {
        Cliente cliente = new Cliente();
        cliente.setClienteId("C002");
        cliente.setContrasena("abcd");
        cliente.setEstado(true);

        Cliente guardado = clienteRepository.save(cliente);

        assertNotNull(guardado.getId(), "El cliente guardado debe tener un ID");
        assertEquals("C002", guardado.getClienteId());
    }
}
