import { Situacao } from './../cadastro-situacao-equipamento/situacao';
import { Equipamento } from './equipamento';

export interface HistoricoEquipamento {

	equipamento: Equipamento,
	situacao: Situacao,
    idUsuario: number
}