package com.energiainteligente.mapeo.Service;

import com.energiainteligente.mapeo.Model.Repository.DispositivoRepository;
import com.energiainteligente.mapeo.Model.entidad.Dispositivo;
import com.energiainteligente.mapeo.Exception.DispositivoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class DispositivoService {

    private final DispositivoRepository dispositivoRepository;

    public List<Dispositivo> listarTodos() {
        return dispositivoRepository.findAll();
    }

    public List<Dispositivo> buscarPorNombre(String termino) {
        return dispositivoRepository.findByNombreContainingIgnoreCase(termino);
    }


    public Optional<Dispositivo> buscarPorId(UUID id) {
        if (id == null) {
            throw new DispositivoException("El ID del dispositivo no puede ser nulo");
        }
        return dispositivoRepository.findById(id);
    }

    public Dispositivo guardar(Dispositivo dispositivo) {
        validarDispositivo(dispositivo);

        // Verificar si ya existe un dispositivo con el mismo nombre
        if (dispositivoRepository.existsByNombre(dispositivo.getNombre())) {
            throw new DispositivoException("Ya existe un dispositivo con el nombre: " + dispositivo.getNombre());
        }

        return dispositivoRepository.save(dispositivo);
    }

    public Optional<Dispositivo> actualizar(UUID id, Dispositivo dispositivoActualizado) {
        if (id == null) {
            throw new DispositivoException("El ID del dispositivo no puede ser nulo");
        }

        validarDispositivo(dispositivoActualizado);

        return dispositivoRepository.findById(id)
                .map(existente -> {
                    // Verificar si el nuevo nombre ya existe en otro dispositivo
                    if (!existente.getNombre().equals(dispositivoActualizado.getNombre()) &&
                            dispositivoRepository.existsByNombre(dispositivoActualizado.getNombre())) {
                        throw new DispositivoException("Ya existe otro dispositivo con el nombre: " + dispositivoActualizado.getNombre());
                    }

                    existente.setNombre(dispositivoActualizado.getNombre());
                    existente.setTipo(dispositivoActualizado.getTipo());
                    existente.setUbicacion(dispositivoActualizado.getUbicacion());
                    existente.setCoordenadas(dispositivoActualizado.getCoordenadas());
                    existente.setEstado(dispositivoActualizado.getEstado());

                    return dispositivoRepository.save(existente);
                });
    }

    public boolean eliminar(UUID id) {
        if (id == null) {
            throw new DispositivoException("El ID del dispositivo no puede ser nulo");
        }

        if (dispositivoRepository.existsById(id)) {
            dispositivoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Dispositivo> buscarPorEstado(Dispositivo.EstadoDispositivo estado) {
        return dispositivoRepository.findByEstado(estado);
    }

    public List<Dispositivo> buscarPorTipo(String tipo) {
        return dispositivoRepository.findByTipoContainingIgnoreCase(tipo);
    }

    private void validarDispositivo(Dispositivo dispositivo) {
        if (dispositivo.getNombre() == null || dispositivo.getNombre().trim().isEmpty()) {
            throw new DispositivoException("El nombre del dispositivo es obligatorio");
        }

        if (dispositivo.getTipo() == null || dispositivo.getTipo().trim().isEmpty()) {
            throw new DispositivoException("El tipo del dispositivo es obligatorio");
        }

        if (dispositivo.getUbicacion() == null || dispositivo.getUbicacion().trim().isEmpty()) {
            throw new DispositivoException("La ubicación del dispositivo es obligatoria");
        }

        if (dispositivo.getCoordenadas() == null || dispositivo.getCoordenadas().trim().isEmpty()) {
            throw new DispositivoException("Las coordenadas del dispositivo son obligatorias");
        }

        if (!dispositivo.getCoordenadas().matches("-?\\d+\\.\\d+,-?\\d+\\.\\d+")) {
            throw new DispositivoException("Formato de coordenadas inválido. Debe ser 'latitud,longitud'");
        }

        if (dispositivo.getEstado() == null) {
            throw new DispositivoException("El estado del dispositivo es obligatorio");
        }
    }
}