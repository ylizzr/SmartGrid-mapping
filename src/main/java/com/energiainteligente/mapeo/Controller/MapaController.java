package com.energiainteligente.mapeo.Controller;

import com.energiainteligente.mapeo.Model.entidad.Dispositivo;
import com.energiainteligente.mapeo.Model.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MapaController {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @GetMapping("/mapa")
    public String mostrarMapa(Model model) {
        List<Dispositivo> dispositivos = dispositivoRepository.findAll();
        model.addAttribute("dispositivos", dispositivos);
        return "index"; // nombre de tu archivo HTML (index.html en templates)
    }
}



