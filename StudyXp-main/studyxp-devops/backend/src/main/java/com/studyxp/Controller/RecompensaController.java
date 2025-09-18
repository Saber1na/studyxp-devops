package com.studyxp.Controller;

import com.studyxp.Model.Recompensa;
import com.studyxp.Service.RecompensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recompensas")
public class RecompensaController {

    @Autowired
    private RecompensaService recompensaService;

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Recompensa> criarRecompensa(@PathVariable Long usuarioId, @RequestBody Recompensa recompensa) {
        return recompensaService.criarRecompensa(usuarioId, recompensa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Recompensa> listarRecompensasPorUsuario(@PathVariable Long usuarioId) {
        return recompensaService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recompensa> buscarPorId(@PathVariable Long id) {
        return recompensaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recompensa> atualizarRecompensa(@PathVariable Long id, @RequestBody Recompensa recompensa) {
        return recompensaService.atualizarRecompensa(id, recompensa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRecompensa(@PathVariable Long id) {
        recompensaService.deletarRecompensa(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/resgatar")
    public ResponseEntity<Recompensa> resgatarRecompensa(@PathVariable Long id) {
        return recompensaService.resgatarRecompensa(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
