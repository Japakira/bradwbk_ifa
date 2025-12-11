package br.edu.ifsp.ifa.services;

import br.edu.ifsp.ifa.models.Atividade;
import br.edu.ifsp.ifa.repositories.AtividadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    private final AtividadeRepository atividadeRepo;

    public AtividadeService(AtividadeRepository atividadeRepo) {
        this.atividadeRepo = atividadeRepo;
    }

    // -------------------------------------------------------------------------
    // LISTAR TODAS
    // -------------------------------------------------------------------------
    @Transactional(readOnly = true)
    public List<Atividade> listar() {
        return atividadeRepo.findAll();
    }

    // -------------------------------------------------------------------------
    // LISTAR POR INICIATIVA
    // -------------------------------------------------------------------------
    @Transactional(readOnly = true)
    public List<Atividade> listarPorIniciativa(Long iniciativaId) {
        return atividadeRepo.findByIniciativaId(iniciativaId);
    }

    // -------------------------------------------------------------------------
    // BUSCAR POR ID
    // -------------------------------------------------------------------------
    @Transactional(readOnly = true)
    public Optional<Atividade> buscarPorId(Long id) {
        return atividadeRepo.findById(id);
    }

    // -------------------------------------------------------------------------
    // CRIAR
    // -------------------------------------------------------------------------
    @Transactional
    public Atividade criar(Atividade a) {

        if (a.getIniciativaId() == null) {
            throw new IllegalArgumentException("A atividade deve estar vinculada a uma iniciativa.");
        }

        // Apenas valida ID. Não carrega entidade porque Atividade não a possui.
        if (a.getIniciativaId() <= 0) {
            throw new IllegalArgumentException("iniciativaId inválido.");
        }

        if (a.getResponsavelId() != null && a.getResponsavelId() <= 0) {
            throw new IllegalArgumentException("responsavelId inválido.");
        }

        return atividadeRepo.save(a);
    }

    // -------------------------------------------------------------------------
    // ATUALIZAR
    // -------------------------------------------------------------------------
    @Transactional
    public Optional<Atividade> atualizar(Long id, Atividade dados) {

        return atividadeRepo.findById(id).map(existing -> {

            existing.setTitulo(dados.getTitulo());
            existing.setDescricao(dados.getDescricao());
            existing.setStatus(dados.getStatus());

            if (dados.getIniciativaId() != null) {
                if (dados.getIniciativaId() <= 0) {
                    throw new IllegalArgumentException("iniciativaId inválido.");
                }
                existing.setIniciativaId(dados.getIniciativaId());
            }

            if (dados.getResponsavelId() != null) {
                if (dados.getResponsavelId() <= 0) {
                    throw new IllegalArgumentException("responsavelId inválido.");
                }
                existing.setResponsavelId(dados.getResponsavelId());
            }

            if (dados.getData() != null) {
                existing.setData(dados.getData());
            }

            return atividadeRepo.save(existing);
        });
    }

    // -------------------------------------------------------------------------
    // DELETAR
    // -------------------------------------------------------------------------
    @Transactional
    public void deletar(Long id) {
        atividadeRepo.deleteById(id);
    }
}
