import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { CadastroDeEquipamentoComponent } from './cadastro-de-equipamento.component'
import { environment } from './../../environments/environment';
import { HistoricoEquipamento } from './historicoEquipamento';
import { Equipamento } from './equipamento';

@Injectable({
    providedIn: 'root'
})
export class EquipamentoService {

    apiUrl = environment.URLSERVIDOR + "equipamentos";

    constructor(private httpClient: HttpClient) { }

    // Headers
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    salvar(novoEquipamento: any): Observable<any>{
        return this.httpClient.post<Equipamento>(this.apiUrl, JSON.stringify(novoEquipamento), this.httpOptions);        
    }

    salvarHistorico(id: string): Observable<any>{
        const dados = {
            equipamento: id,
            situacaoEquipamento: "1"
        } as HistoricoEquipamento;

        return this.httpClient.post(environment.URLSERVIDOR + "historicoEquipamento" , JSON.stringify(dados), this.httpOptions)
        .pipe(retry(2),catchError(this.handleError))
        //return this.httpClient.post(this.apiUrl, novoUser);
        
    }

    // Manipulação de erros
    handleError(error: HttpErrorResponse) {
        let errorMessage = '';
        if (error.error instanceof ErrorEvent) {
        // Erro ocorreu no lado do client
        errorMessage = error.error.message;
        } else {
        // Erro ocorreu no lado do servidor
        errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
        }
        console.log(errorMessage);
        return throwError(errorMessage);
    };

    getTiposEquipamentos(): Observable<any> {
        return this.httpClient.get<any>(environment.URLSERVIDOR + "tipoEquipamentos");
      }
    
      getEquipamentos(): Observable<any> {
        return this.httpClient.get<any>(this.apiUrl);
      }

}