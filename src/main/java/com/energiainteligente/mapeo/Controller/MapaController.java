package com.energiainteligente.mapeo.Controller;

import com.energiainteligente.mapeo.Service.DispositivoService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapaController {

    private final DispositivoService dispositivoService;

    public MapaController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @GetMapping("/mapa")
    public String mostrarMapa(Model model) {
        model.addAttribute("dispositivos", dispositivoService.listarTodos());
        return "mapa";
    }
}

