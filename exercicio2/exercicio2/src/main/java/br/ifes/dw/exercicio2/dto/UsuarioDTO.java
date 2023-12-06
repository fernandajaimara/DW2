package br.ifes.dw.exercicio2.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class UsuarioDTO {

		private String nome;
		private Date dataNasc;
		private List<EnderecoDTO> enderecos;
}
