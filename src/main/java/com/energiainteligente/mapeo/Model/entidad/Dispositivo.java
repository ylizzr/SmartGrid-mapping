package com.energiainteligente.mapeo.Model.entidad;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoDispositivo estado;

    @Column(name = "fecha_instalacion", nullable = false)
    private LocalDateTime fechaInstalacion;

    public enum EstadoDispositivo {
        Activo,
        Inactivo,
        Mantenimiento
    }
}

