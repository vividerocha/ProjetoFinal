import { TipoEquipamento } from './tipoEquipamento';

export interface Equipamento {
    id: number,
    descricao: String,
    funcionando: Boolean,
    tipoEquipamento: TipoEquipamento
}