import { TipoEquipamento } from '../cadastro-tipo-equipamento/tipoEquipamento';

export interface Equipamento {
    id: number,
    descricaoEquipamento: String,
    funcionando: Boolean,
    tipoEquipamento: TipoEquipamento,
    idUsuario: number
}