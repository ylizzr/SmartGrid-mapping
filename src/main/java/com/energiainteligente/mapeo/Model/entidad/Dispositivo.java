package com.energiainteligente.mapeo.Model.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "dispositivos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dispositivo {

    @Id
    @Column(name = "id_dispositivo", nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private String coordenadas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoDispositivo estado;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    public enum EstadoDispositivo {
        Activo,
        Inactivo,
        Mantenimiento
    }
}