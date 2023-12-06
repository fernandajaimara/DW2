package br.ifes.dw.exercicio2.controller;

import br.ifes.dw.exercicio2.dto.UsuarioDTO;
import br.ifes.dw.exercicio2.model.Usuario;
import br.ifes.dw.exercicio2.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

		@Autowired
		private UsuarioService usuarioService;

		@GetMapping
		public List<Usuario> getAllUsuarios() {
				return usuarioService.getAllUsuarios();
		}

		@GetMapping("/{id}")
		public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
				Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
			return usuario.map(ResponseEntity::ok).orElseGet(() -> 	ResponseEntity.notFound().build());
		}

		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Usuario criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
				return usuarioService.criarUsuario(usuarioDTO);
		}

		@PutMapping("/{id}")
		public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
				Optional<Usuario> usuario = usuarioService.atualizarUsuario(id, usuarioDTO);
				return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
				boolean deletado = usuarioService.deletarUsuario(id);
				return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
		}
}
