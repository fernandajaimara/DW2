package br.ifes.dw.exercicio2.service;

import br.ifes.dw.exercicio2.dto.EnderecoDTO;
import br.ifes.dw.exercicio2.model.Endereco;
import br.ifes.dw.exercicio2.repository.EnderecoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> getEnderecoById(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco criarEndereco(EnderecoDTO enderecoDTO) {
        return enderecoRepository.save(converterDTO(enderecoDTO));
    }

    public Optional<Endereco> atualizarEndereco(Long id, EnderecoDTO enderecoDTO) {
        return enderecoRepository.findById(id).map(endereco -> {
            Endereco enderecoAtualizado = converterDTO(enderecoDTO);
            enderecoAtualizado.setId(endereco.getId());
            return enderecoRepository.save(enderecoAtualizado);
        });
    }

    public boolean deletarEndereco(Long id) {
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private Endereco converterDTO(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        return endereco;
    }
}
