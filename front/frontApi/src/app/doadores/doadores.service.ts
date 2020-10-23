import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { Doador } from './../pageDoador/form-doador/doador';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})

export class DoadoresService {

    apiUrl = environment.URLSERVIDOR + "doadores";

    constructor(private httpClient: HttpClient) { }

    // Headers
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
     }

    getDoadores(): Observable<any>{
        return this.httpClient.get<Doador>(this.apiUrl);
    }

    atualizar(id, data): Observable<any> {
        return this.httpClient.put(this.apiUrl + "/id", data);
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

