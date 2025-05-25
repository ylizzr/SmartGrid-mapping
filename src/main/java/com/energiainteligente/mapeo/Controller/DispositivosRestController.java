package com.energiainteligente.mapeo.Controller;

import com.energiainteligente.mapeo.Model.entidad.Dispositivo;
import com.energiainteligente.mapeo.Service.DispositivoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivosRestController {

    private final DispositivoService dispositivoService;

    public DispositivosRestController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }


    @GetMapping
    public ResponseEntity<List<Dispositivo>> obtenerDispositivos(
            @RequestParam(value = "search", required = false) String search) {
        List<Dispositivo> dispositivos;
        if (search != null && !search.trim().isEmpty()) {
            dispositivos = dispositivoService.buscarPorNombre(search.trim());
        } else {
            dispositivos = dispositivoService.listarTodos();
        }
        return ResponseEntity.ok(dispositivos);
    }


    @PostMapping
    public ResponseEntity<Dispositivo> crearDispositivo(@RequestBody Dispositivo dispositivo) {
        if (dispositivo.getId() == null) {
            dispositivo.setId(UUID.randomUUID());
        }
        Dispositivo nuevo = dispositivoService.guardar(dispositivo);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> obtenerPorId(@PathVariable UUID id) {
        return dispositivoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dispositivo> actualizarDispositivo(
            @PathVariable UUID id,
            @RequestBody Dispositivo dispositivo) {
        return dispositivoService.actualizar(id, dispositivo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDispositivo(@PathVariable UUID id) {
        if (dispositivoService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}