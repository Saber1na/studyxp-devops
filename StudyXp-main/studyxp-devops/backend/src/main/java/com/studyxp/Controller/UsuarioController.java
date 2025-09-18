package com.studyxp.Controller;

import com.studyxp.Model.Tarefa;
import com.studyxp.Model.Usuario;
import com.studyxp.Service.TarefaService;
import com.studyxp.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        if (usuarioService.emailExiste(usuario.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(id, usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{usuarioId}/perfil")
    public ResponseEntity<Map<String, Object>> obterPerfil(@PathVariable Long usuarioId) {
        return usuarioService.buscarPorId(usuarioId).map(usuario -> {
            List<Tarefa> tarefas = tarefaService.listarTarefasPorUsuario(usuarioId);

            Map<String, Object> perfil = new HashMap<>();
            perfil.put("id", usuario.getId());
            perfil.put("nome", usuario.getNome());
            perfil.put("email", usuario.getEmail());
            perfil.put("pontos", usuario.getPontos());
            perfil.put("tarefas", tarefas);

            return ResponseEntity.ok(perfil);
        }).orElse(ResponseEntity.notFound().build());
    }
}
