package br.com.digitalhouse.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Long id;
    private String nome;
    private String email;
    private String senha;
    
//    public Usuario transformaParaObjeto(){
//        return new Usuario(nome, email, senha);
//    }
//
//	@Override
//	public String toString() {
//		return "UsuarioDTO [nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
//	}
    
}
