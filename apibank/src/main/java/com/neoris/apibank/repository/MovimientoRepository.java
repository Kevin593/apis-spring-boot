package com.neoris.apibank.repository;

import com.neoris.apibank.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaClienteIdAndFechaBetween(Long clienteId, LocalDateTime from, LocalDateTime to);
    List<Movimiento> findByCuentaIdOrderByFechaDesc(Long cuentaId);
}

