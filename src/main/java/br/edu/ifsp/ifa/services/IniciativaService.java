package br.edu.ifsp.ifa.services;

import br.edu.ifsp.ifa.models.Iniciativa;
import br.edu.ifsp.ifa.repositories.IniciativaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IniciativaService {

    private final IniciativaRepository repository;

    public IniciativaService(IniciativaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Iniciativa> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Iniciativa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Iniciativa criar(Iniciativa iniciativa) {
        return repository.save(iniciativa);
    }

    @Transactional
    public Optional<Iniciativa> atualizar(Long id, Iniciativa dados) {
        return repository.findById(id).map(existing -> {

            existing.setTitulo(dados.getTitulo());
            existing.setDescricao(dados.getDescricao());

            // Atualiza responsável caso venha preenchido
            existing.setResponsavel(dados.getResponsavel());

            return repository.save(existing);
        });
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
