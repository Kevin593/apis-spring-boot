package com.neoris.apibank.controller;

import com.neoris.apibank.entity.Cliente;
import com.neoris.apibank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() { return clienteRepository.findAll(); }

    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) { return clienteRepository.save(cliente); }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente ex = clienteRepository.findById(id).orElseThrow();
        ex.setNombre(cliente.getNombre());
        ex.setGenero(cliente.getGenero());
        ex.setEdad(cliente.getEdad());
        ex.setIdentificacion(cliente.getIdentificacion());
        ex.setDireccion(cliente.getDireccion());
        ex.setTelefono(cliente.getTelefono());
        ex.setClienteId(cliente.getClienteId());
        ex.setContrasena(cliente.getContrasena());
        ex.setEstado(cliente.getEstado());
        return clienteRepository.save(ex);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) { clienteRepository.deleteById(id); }
}
