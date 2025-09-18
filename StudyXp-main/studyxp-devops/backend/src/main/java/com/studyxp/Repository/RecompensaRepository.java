package com.studyxp.Repository;

import com.studyxp.Model.Recompensa;
import com.studyxp.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecompensaRepository extends JpaRepository<Recompensa, Long> {
    List<Recompensa> findByUsuario(Usuario usuario);
}
