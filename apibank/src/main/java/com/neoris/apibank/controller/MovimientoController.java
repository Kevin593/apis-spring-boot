package com.neoris.apibank.controller;

import com.neoris.apibank.entity.Movimiento;
import com.neoris.apibank.repository.MovimientoRepository;
import com.neoris.apibank.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<Movimiento> listar() { return movimientoRepository.findAll(); }

    @PostMapping
    public Movimiento crear(@RequestBody MovimientoRequestDto dto) {
        LocalDateTime fecha = dto.getFecha();
        return movimientoService.crearMovimiento(dto.getCuentaId(), dto.getTipoMovimiento(), dto.getValor(), fecha);
    }

    @PutMapping("/{id}")
    public Movimiento actualizar(@PathVariable Long id, @RequestBody Movimiento m) {
        Movimiento ex = movimientoRepository.findById(id).orElseThrow();
        ex.setFecha(m.getFecha());
        ex.setTipoMovimiento(m.getTipoMovimiento());
        ex.setValor(m.getValor());
        return movimientoRepository.save(ex);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) { movimientoRepository.deleteById(id); }

    // Buscar movimientos por cliente y rango de fechas (ISO datetimes en query)
    @GetMapping("/por-cliente")
    public List<Movimiento> porClienteYFechas(@RequestParam Long clienteId,
                                              @RequestParam String from,
                                              @RequestParam String to) {
        LocalDateTime f = LocalDateTime.parse(from);
        LocalDateTime t = LocalDateTime.parse(to);
        return movimientoRepository.findByCuentaClienteIdAndFechaBetween(clienteId, f, t);
    }
}
