import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { Mensagem } from './mensagem';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})

export class ContatoService {

    apiUrl = environment.URLSERVIDOR + "emailSend";

    constructor(private httpClient: HttpClient) { }

    // Headers
    //httpOptions = {
    //    headers: new HttpHeaders({'Content-Type': 'application/json' })
    //}
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

        enviarEmail(mensagem: Mensagem): Observable<any>{
            return this.httpClient.post<Mensagem>(this.apiUrl, mensagem, this.httpOptions);     
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
}