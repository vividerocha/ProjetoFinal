import { TipoEquipamento } from './tipoEquipamento';

export interface Equipamento {
    id: number,
    descricaoEquipamento: String,
    funcionando: Boolean,
    tipoEquipamento: TipoEquipamento
}