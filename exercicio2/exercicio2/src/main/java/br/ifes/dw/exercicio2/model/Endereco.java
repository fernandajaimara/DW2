package br.ifes.dw.exercicio2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Endereco{

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String logradouro;
		private int numero;
		private String bairro;
		private String cidade;
		private String estado;

		@ManyToOne
		@JoinColumn(name = "usuario_id")
		private Usuario usuario; 
}
