package br.ifes.dw.exercicio2.dto;

import lombok.Data;

@Data
public class EnderecoDTO {

		private String logradouro;
		private int numero;
		private String bairro;
		private String cidade;
		private String estado;
}
