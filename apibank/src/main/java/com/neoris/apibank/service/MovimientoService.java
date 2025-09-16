package com.neoris.apibank.service;

import com.neoris.apibank.entity.Cuenta;
import com.neoris.apibank.entity.Movimiento;
import com.neoris.apibank.exception.SaldoInsuficienteException;
import com.neoris.apibank.repository.CuentaRepository;
import com.neoris.apibank.repository.MovimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Transactional
    public Movimiento crearMovimiento(Long cuentaId, String tipoMovimiento, Double valor, LocalDateTime fecha) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada: " + cuentaId));

        // cantidad: si usuario pasa valor negativo/positivo, consideramos valor positivo y tipo define
        double monto = valor != null ? valor : 0.0;
        double saldoActual = cuenta.getSaldoInicial() != null ? cuenta.getSaldoInicial() : 0.0;
        double nuevoSaldo;
        if ("Retiro".equalsIgnoreCase(tipoMovimiento)) {
            nuevoSaldo = saldoActual - monto;
        } else { // Deposito u otros (tratamos todo lo demás como ingreso)
            nuevoSaldo = saldoActual + monto;
        }

        if (nuevoSaldo < 0) {
            throw new SaldoInsuficienteException("Saldo no disponible");
        }

        // actualizar cuenta
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento mov = new Movimiento();
        mov.setCuenta(cuenta);
        mov.setFecha(fecha != null ? fecha : LocalDateTime.now());
        mov.setTipoMovimiento(tipoMovimiento);
        mov.setValor(monto);
        mov.setSaldo(nuevoSaldo);

        return movimientoRepository.save(mov);
    }
}

