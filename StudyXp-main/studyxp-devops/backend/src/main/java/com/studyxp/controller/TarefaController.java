package com.studyxp.controller;

import com.studyxp.model.Tarefa;
import com.studyxp.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<Tarefa>> getAllTarefas() {
        List<Tarefa> tarefas = tarefaService.listarTodasAsTarefas();
        return ResponseEntity.ok(tarefas);
    }
}