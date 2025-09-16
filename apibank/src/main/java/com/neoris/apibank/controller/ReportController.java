package com.neoris.apibank.controller;

import com.neoris.apibank.entity.Cuenta;
import com.neoris.apibank.entity.Movimiento;
import com.neoris.apibank.repository.CuentaRepository;
import com.neoris.apibank.repository.MovimientoRepository;
import com.neoris.apibank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/reportes")
public class ReportController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @GetMapping
    public Map<String,Object> reporteEstadoCuenta(@RequestParam Long clienteId,
                                                  @RequestParam String from,
                                                  @RequestParam String to) {
        var cliente = clienteRepository.findById(clienteId).orElseThrow();
        LocalDateTime f = LocalDateTime.parse(from);
        LocalDateTime t = LocalDateTime.parse(to);

        // cuentas asociadas
        List<Cuenta> cuentas = cuentaRepository.findAll().stream()
                .filter(c -> c.getCliente() != null && Objects.equals(c.getCliente().getId(), clienteId))
                .toList();

        List<Map<String,Object>> cuentasInfo = new ArrayList<>();
        for (Cuenta c : cuentas) {
            Map<String,Object> cmap = new HashMap<>();
            cmap.put("numeroCuenta", c.getNumeroCuenta());
            cmap.put("tipoCuenta", c.getTipoCuenta());
            cmap.put("saldoActual", c.getSaldoInicial());
            cmap.put("estado", c.getEstado());

            // movimientos de la cuenta en el rango
            List<Movimiento> movs = movimientoRepository.findByCuentaIdOrderByFechaDesc(c.getId());
            List<Map<String,Object>> movsEnRango = movs.stream()
                    .filter(m -> !m.getFecha().isBefore(f) && !m.getFecha().isAfter(t))
                    .map(m -> {
                        Map<String,Object> mm = new HashMap<>();
                        mm.put("fecha", m.getFecha());
                        mm.put("tipo", m.getTipoMovimiento());
                        mm.put("valor", m.getValor());
                        mm.put("saldo", m.getSaldo());
                        return mm;
                    }).toList();

            cmap.put("movimientos", movsEnRango);
            cuentasInfo.add(cmap);
        }

        Map<String,Object> result = new HashMap<>();
        result.put("clienteId", cliente.getId());
        result.put("clienteNombre", cliente.getNombre());
        result.put("from", f);
        result.put("to", t);
        result.put("cuentas", cuentasInfo);
        return result;
    }
}

