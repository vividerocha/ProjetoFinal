import { Usuario } from "src/app/cadastro-do-usuario/usuario";

export interface Doador {
	id: number,
  	nomeCompleto: String,
	cep: String,
	logradouro: String,
	numeroCasa: number,
	bairro: String,
	cidade: String,
	estado: String,
	complemento: String,
	telefone: String,
	celular: String,
	termo: String,
	usuario: Usuario 
  }