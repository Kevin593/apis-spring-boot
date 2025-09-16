package com.neoris.apibank.controller;

import com.neoris.apibank.entity.Cliente;
import com.neoris.apibank.entity.Cuenta;
import com.neoris.apibank.repository.ClienteRepository;
import com.neoris.apibank.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cuenta> listar() { return cuentaRepository.findAll(); }

    @PostMapping
    public Cuenta crear(@RequestBody Cuenta input) {
        // si vienen cliente.id al JSON, asocia
        if (input.getCliente() != null && input.getCliente().getId() != null) {
            Cliente cliente = clienteRepository.findById(input.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            input.setCliente(cliente);
        }
        return cuentaRepository.save(input);
    }

    @PutMapping("/{id}")
    public Cuenta actualizar(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        Cuenta ex = cuentaRepository.findById(id).orElseThrow();
        ex.setNumeroCuenta(cuenta.getNumeroCuenta());
        ex.setTipoCuenta(cuenta.getTipoCuenta());
        ex.setSaldoInicial(cuenta.getSaldoInicial());
        ex.setEstado(cuenta.getEstado());
        if (cuenta.getCliente() != null && cuenta.getCliente().getId() != null) {
            ex.setCliente(clienteRepository.findById(cuenta.getCliente().getId()).orElseThrow());
        }
        return cuentaRepository.save(ex);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) { cuentaRepository.deleteById(id); }
}
