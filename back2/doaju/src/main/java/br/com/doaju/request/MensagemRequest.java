package br.com.doaju.request;

import lombok.Data;

@Data
public class MensagemRequest {
	
	private Long id;
	private String destinatario;
	private String assunto;
	private String mensagem;

}
