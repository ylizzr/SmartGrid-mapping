
package com.energiainteligente.mapeo.Service;

import com.energiainteligente.mapeo.Model.Repository.DispositivoRepository;
import com.energiainteligente.mapeo.Model.entidad.Dispositivo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoService {

    private final DispositivoRepository dispositivoRepository;

    public DispositivoService(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    public List<Dispositivo> listarTodos() {
        return dispositivoRepository.findAll();
    }
}
