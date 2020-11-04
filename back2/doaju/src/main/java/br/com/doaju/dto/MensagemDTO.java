package br.com.doaju.dto;

import lombok.Data;

@Data
public class MensagemDTO {
	
		private Long id;
		private String destinatario;
		private String assunto;
		private String mensagem;	
}
