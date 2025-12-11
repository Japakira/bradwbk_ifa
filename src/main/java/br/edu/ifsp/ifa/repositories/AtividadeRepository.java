package br.edu.ifsp.ifa.repositories;

import br.edu.ifsp.ifa.models.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    // Busca atividades por iniciativa_id
    List<Atividade> findByIniciativaId(Long iniciativaId);

    // Busca atividades por responsavel_id (opcional)
    List<Atividade> findByResponsavelId(Long responsavelId);
}
