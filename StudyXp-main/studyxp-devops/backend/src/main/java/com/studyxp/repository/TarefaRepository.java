package com.studyxp.repository;

import com.studyxp.model.Tarefa;
import com.studyxp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByUsuario(Usuario usuario);
    List<Tarefa> findByUsuarioId(Long usuarioId);
}
