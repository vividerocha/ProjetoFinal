package br.com.digitalhouse.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class UsuarioRequest {
	
	private Long id;
	@NotNull
	private String nome;
	@NotNull
    private String email;
	@NotNull
    private String senha;
}
