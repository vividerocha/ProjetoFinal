import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Situacao } from './situacao';

@Injectable({
    providedIn: 'root'
})
export class SituacaoEquipamentoService {

    apiUrl = environment.URLSERVIDOR + "situacaoEquipamento";

    constructor(private httpClient: HttpClient) { }

    // Headers
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    salvar(novo: any): Observable<any>{
        return this.httpClient.post(this.apiUrl, JSON.stringify(novo), this.httpOptions)
        .pipe(retry(2),catchError(this.handleError))        
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

    getSituacoesEquipamentos(): Observable<any> {
        return this.httpClient.get<any>(this.apiUrl);
    }

    verificaSituacao(desc: string): Observable<any>{
        return this.httpClient.get<Situacao>(this.apiUrl + '/desc/' + desc);
    }

    getSituacao(id: number): Observable<any>{
        return this.httpClient.get<Situacao>(this.apiUrl + "/" + id);
    }

    delete(id: number): Observable<any> {
        return this.httpClient.delete(`${this.apiUrl}/${id}`);
    }
    atualizar(id: number, situacao: Situacao){   
        return this.httpClient.put(`${this.apiUrl}/${id}`, situacao)
        .pipe(retry(2),catchError(this.handleError));
    }

}