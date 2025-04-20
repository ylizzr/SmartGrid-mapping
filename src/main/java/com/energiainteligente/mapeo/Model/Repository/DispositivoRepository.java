
package com.energiainteligente.mapeo.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.energiainteligente.mapeo.Model.entidad.Dispositivo;

import java.util.UUID;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {
}
