package br.ifes.dw.exercicio2.service;

import br.ifes.dw.exercicio2.model.Usuario;
import br.ifes.dw.exercicio2.repository.UsuarioRepository;
import br.ifes.dw.exercicio2.dto.UsuarioDTO;
import br.ifes.dw.exercicio2.model.Endereco;
import br.ifes.dw.exercicio2.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario criarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = converterDTO(usuarioDTO);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    Usuario usuarioAtualizado = converterDTO(usuarioDTO);
                    usuarioAtualizado.setId(usuario.getId());
                    return usuarioRepository.save(usuarioAtualizado);
                });
    }

    public boolean deletarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private Usuario converterDTO(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setDataNasc(usuarioDTO.getDataNasc());

        List<Endereco> enderecos = usuarioDTO.getEnderecos().stream()
                .map(this::converterEnderecoDTO)
                .collect(Collectors.toList());

        usuario.setEnderecos(enderecos);
        return usuario;
    }

    private Endereco converterEnderecoDTO(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        return endereco;
    }
}
