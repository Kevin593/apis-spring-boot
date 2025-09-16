package com.neoris.apibank.controller;

import java.time.LocalDateTime;

public class MovimientoRequestDto {
    private Long cuentaId;
    private String tipoMovimiento;
    private Double valor;
    private LocalDateTime fecha; // opcional

    // getters y setters
    public Long getCuentaId() { return cuentaId; }
    public void setCuentaId(Long cuentaId) { this.cuentaId = cuentaId; }
    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
