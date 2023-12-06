package br.ifes.dw.exercicio2.controller;

import br.ifes.dw.exercicio2.dto.EnderecoDTO;
import br.ifes.dw.exercicio2.model.Endereco;
import br.ifes.dw.exercicio2.service.EnderecoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

		@Autowired
		private EnderecoService enderecoService;

		@GetMapping
		public List<Endereco> getAllEnderecos() {
				return enderecoService.getAllEnderecos();
		}

		@GetMapping("/{id}")
		public ResponseEntity<Endereco> getEnderecoById(@PathVariable Long id) {
				Optional<Endereco> endereco = enderecoService.getEnderecoById(id);
				return endereco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		}

		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Endereco criarEndereco(@RequestBody EnderecoDTO enderecoDTO) {
				return enderecoService.criarEndereco(enderecoDTO);
		}

		@PutMapping("/{id}")
		public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
				Optional<Endereco> endereco = enderecoService.atualizarEndereco(id, enderecoDTO);
				return endereco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
				boolean deletado = enderecoService.deletarEndereco(id);
				return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
		}
}
