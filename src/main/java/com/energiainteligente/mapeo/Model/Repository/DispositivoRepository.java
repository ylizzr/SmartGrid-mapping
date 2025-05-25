
package com.energiainteligente.mapeo.Model.Repository;


import com.energiainteligente.mapeo.Model.entidad.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {
    boolean existsByNombre(String nombre);
    List<Dispositivo> findByEstado(Dispositivo.EstadoDispositivo estado);
    List<Dispositivo> findByTipoContainingIgnoreCase(String tipo);
    List<Dispositivo> findByNombreContainingIgnoreCase(String nombre);

}
