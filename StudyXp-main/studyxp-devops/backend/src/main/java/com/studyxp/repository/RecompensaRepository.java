package com.studyxp.repository;

import com.studyxp.model.Recompensa;
import com.studyxp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecompensaRepository extends JpaRepository<Recompensa, Long> {
    List<Recompensa> findByUsuario(Usuario usuario);
}
