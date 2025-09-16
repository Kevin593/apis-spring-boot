package com.neoris.apibank.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona {

    // la PK viene de Persona (id)
    @Column(name = "cliente_id", unique = true)
    private String clienteId; // identificador único (ej C001)

    @Column(name = "contrasena")
    private String contrasena;

    private Boolean estado;

    // Getters y setters
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}
