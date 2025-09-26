package com.studyxp.service;

import com.studyxp.model.Recompensa;
import com.studyxp.model.Usuario;
import com.studyxp.repository.RecompensaRepository;
import com.studyxp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecompensaService {
    @Autowired
    private RecompensaRepository recompensaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Recompensa> criarRecompensa(Long usuarioId, Recompensa recompensa) {
        return usuarioRepository.findById(usuarioId).map(usuario -> {
            recompensa.setUsuario(usuario);
            return recompensaRepository.save(recompensa);
        });
    }

    public List<Recompensa> listarPorUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .map(recompensaRepository::findByUsuario)
                .orElse(List.of());
    }

    public Optional<Recompensa> buscarPorId(Long id) {
        return recompensaRepository.findById(id);
    }

    public Optional<Recompensa> atualizarRecompensa(Long id, Recompensa novaRecompensa) {
        return recompensaRepository.findById(id).map(recompensa -> {
            recompensa.setDescricao(novaRecompensa.getDescricao());
            recompensa.setCustoPontos(novaRecompensa.getCustoPontos());
            return recompensaRepository.save(recompensa);
        });
    }

    public void deletarRecompensa(Long id) {
        recompensaRepository.deleteById(id);
    }

    public Optional<Recompensa> resgatarRecompensa(Long recompensaId) {
        return recompensaRepository.findById(recompensaId).flatMap(recompensa -> {
            Usuario usuario = recompensa.getUsuario();
            if (usuario.getPontos() >= recompensa.getCustoPontos()) {
                usuario.setPontos(usuario.getPontos() - recompensa.getCustoPontos());
                usuarioRepository.save(usuario);
                return Optional.of(recompensa);
            } else {
                return Optional.empty();
            }
        });
    }
}
