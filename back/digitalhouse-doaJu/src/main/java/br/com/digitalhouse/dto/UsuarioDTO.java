package br.com.digitalhouse.dto;

import br.com.digitalhouse.model.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    
    public Usuario transformaParaObjeto(){
        return new Usuario(nome, email, senha);
    }
}
