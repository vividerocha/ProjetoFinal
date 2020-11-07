import { Aluno } from "./../cadastro-aluno/aluno";

export interface Questionario {
    id: number,
    perg1: number,
    perg2: number,
    perg3: number,
    perg4: number,
    perg5: number,
    perg6: number,
    perg7: number,
    perg8: number,
    perg9: number,
    perg10: number,
    aluno: number,
    pontuacaoTotal: number
}