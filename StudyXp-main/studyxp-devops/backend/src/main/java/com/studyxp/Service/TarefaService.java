package com.studyxp.Service;

import com.studyxp.Model.StatusTarefa;
import com.studyxp.Model.Tarefa;
import com.studyxp.Model.Usuario;
import com.studyxp.Repository.TarefaRepository;
import com.studyxp.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Tarefa> criarTarefa(Long usuarioId, Tarefa tarefa) {
        return usuarioRepository.findById(usuarioId).map(usuario -> {
            tarefa.setUsuario(usuario);
            tarefa.setStatus(StatusTarefa.A_FAZER);
            tarefa.setDataCriacao(LocalDateTime.now());
            tarefa.setPontuacao(tarefa.getPontuacao() != null ? tarefa.getPontuacao() : 10);
            return tarefaRepository.save(tarefa);
        });
    }

    public List<Tarefa> listarTarefasPorUsuario(Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        return usuario.map(tarefaRepository::findByUsuario).orElse(List.of());
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    public Optional<Tarefa> atualizarTarefa(Long id, Tarefa novaTarefa) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setTitulo(novaTarefa.getTitulo());
            tarefa.setDescricao(novaTarefa.getDescricao());
            tarefa.setStatus(novaTarefa.getStatus());
            return tarefaRepository.save(tarefa);
        });
    }

    public Optional<Tarefa> concluirTarefa(Long tarefaId) {
        return tarefaRepository.findById(tarefaId).map(tarefa -> {
            if (tarefa.getStatus() != StatusTarefa.CONCLUIDA) {
                tarefa.setStatus(StatusTarefa.CONCLUIDA);
                tarefa.setDataConclusao(LocalDateTime.now());
                tarefaRepository.save(tarefa);

                Usuario usuario = tarefa.getUsuario();
                usuario.setPontos(usuario.getPontos() + tarefa.getPontuacao());
                usuarioRepository.save(usuario);
            }
            return tarefa;
        });
    }

    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
}
