package com.energiainteligente.mapeo.Controller;

import com.energiainteligente.mapeo.Model.entidad.Dispositivo;
import com.energiainteligente.mapeo.Service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DispositivosRestController {
    
    @Autowired
    private DispositivoService dispositivoService;
    
    @GetMapping("/dispositivos")
    public List<Dispositivo> obtenerDispositivos() {
        return dispositivoService.listarTodos();
    }
}