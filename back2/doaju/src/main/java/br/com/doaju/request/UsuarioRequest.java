package br.com.doaju.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

//@Getter
@Data
public class UsuarioRequest {
	
	private Long id;
	@NotNull
	private String usuario;
	@NotNull
    private String email;
	@NotNull
    private String senha;
	
	private String tipoPermissao;
}
