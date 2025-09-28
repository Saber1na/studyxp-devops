package com.studyxp.service;

import com.studyxp.model.StatusTarefa;
import com.studyxp.model.Tarefa;
import com.studyxp.model.Usuario;
import com.studyxp.repository.TarefaRepository;
import com.studyxp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Tarefa> listarTodasAsTarefas() {
        // Para simplificar, vamos buscar as tarefas do usuário 1 por padrão
        return tarefaRepository.findByUsuarioId(1L);
    }

    public Tarefa criarNovaTarefa(Tarefa tarefa) {
        // Busca o usuário padrão (com ID 1) no banco de dados.
        Usuario usuario = usuarioRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Usuário com ID 1 não encontrado!"));

        // Associa o usuário encontrado à nova tarefa
        tarefa.setUsuario(usuario);

        // Define o status padrão
        tarefa.setStatus(StatusTarefa.PENDENTE);

        // Salva a tarefa agora com a associação correta
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefasPorUsuario(Long usuarioId) {
        return tarefaRepository.findByUsuarioId(usuarioId);
    }
}