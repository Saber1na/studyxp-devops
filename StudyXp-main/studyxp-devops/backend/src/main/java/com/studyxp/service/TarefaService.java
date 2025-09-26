package com.studyxp.service;

import com.studyxp.model.Tarefa;
import com.studyxp.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTodasAsTarefas() {
        return tarefaRepository.findAll();
    }

    public List<Tarefa> listarTarefasPorUsuario(Long usuarioId) {
        return tarefaRepository.findByUsuarioId(usuarioId);
    }
}