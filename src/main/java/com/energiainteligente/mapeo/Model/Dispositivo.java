package com.energiainteligente.mapeo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dispositivo {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;
    private String tipo;
    private String ubicacion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    public enum Estado {
        Activo, Inactivo, Mantenimiento
    }
}
