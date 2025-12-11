package br.edu.ifsp.ifa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsp.ifa.dtos.ResponsavelDTO;
import br.edu.ifsp.ifa.models.Responsavel;
import br.edu.ifsp.ifa.models.Iniciativa;
import br.edu.ifsp.ifa.repositories.ResponsavelRepository;
import br.edu.ifsp.ifa.repositories.IniciativaRepository;

@Service
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;
    private final IniciativaRepository iniciativaRepository;

    public ResponsavelService(ResponsavelRepository responsavelRepository,
                              IniciativaRepository iniciativaRepository) {
        this.responsavelRepository = responsavelRepository;
        this.iniciativaRepository = iniciativaRepository;
    }

    // LISTAR
    public List<ResponsavelDTO> listar() {
        return responsavelRepository.findAll()
                .stream()
                .map(ResponsavelDTO::fromEntity)
                .toList();
    }

    // BUSCAR POR ID
    public ResponsavelDTO buscarPorId(Long id) {
        Responsavel responsavel = responsavelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));
        return ResponsavelDTO.fromEntity(responsavel);
    }

    // CRIAR
    public ResponsavelDTO criar(ResponsavelDTO dto) {
        Responsavel responsavel = dto.toEntity();
        responsavel = responsavelRepository.save(responsavel);
        return ResponsavelDTO.fromEntity(responsavel);
    }

    // ATUALIZAR
    public ResponsavelDTO atualizar(Long id, ResponsavelDTO dto) {
        Responsavel responsavel = responsavelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));

        responsavel.setNome(dto.getNome());
        responsavel.setEmail(dto.getEmail());

        responsavel = responsavelRepository.save(responsavel);

        return ResponsavelDTO.fromEntity(responsavel);
    }

    // DELETAR
    public void deletar(Long id) {
        if (!responsavelRepository.existsById(id)) {
            throw new RuntimeException("Responsável não encontrado");
        }
        responsavelRepository.deleteById(id);
    }

    // ASSOCIAR RESPONSÁVEL À INICIATIVA
    public void associarResponsavelIniciativa(Long idResponsavel, Long idIniciativa) {

        Responsavel responsavel = responsavelRepository.findById(idResponsavel)
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));

        Iniciativa iniciativa = iniciativaRepository.findById(idIniciativa)
                .orElseThrow(() -> new RuntimeException("Iniciativa não encontrada"));

        iniciativa.setResponsavel(responsavel);

        iniciativaRepository.save(iniciativa);
    }
}
