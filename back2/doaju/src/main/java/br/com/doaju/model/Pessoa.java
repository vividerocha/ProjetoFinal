package br.com.doaju.model;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Embeddable
public class Pessoa {
		
		private String nomeCompleto;	
		private String cep;
		private String logradouro;
		private int numeroCasa;
		private String bairro;
		private String cidade;
		private String estado;
		private String complemento;
		private String telefone;
		private String celular;
		private boolean termo;
		
		@DateTimeFormat(pattern="yyyy-mm-dd")
		private LocalDate dataCadastro;
		
		public Pessoa() {
			
		}
}
