package br.com.doaju.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

//@Getter
@Data
public class UsuarioRequest {
	
	private Long id;
	@NotNull
	private String nome;
	@NotNull
    private String email;
	@NotNull
    private String senha;
}
