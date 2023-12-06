package br.ifes.dw.exercicio2.model;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Usuario{

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String nome;
		private Date dataNasc;

		@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
		private List<Endereco> enderecos;
}
