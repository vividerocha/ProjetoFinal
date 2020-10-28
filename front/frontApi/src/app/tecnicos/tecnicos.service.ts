import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { Doador } from './../pageDoador/form-doador/doador';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Tecnico } from './../cadastro-tecnico/tecnico';

@Injectable({
    providedIn: 'root'
})

export class TecnicosService {

    apiUrl = environment.URLSERVIDOR + "tecnicos";

    constructor(private httpClient: HttpClient) { }

    // Headers
    //httpOptions = {
    //    headers: new HttpHeaders({'Content-Type': 'application/json' })
    //}
    httpOptions = {
        header : new HttpHeaders(   {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin' : 'localhost:8080',
            'Access-Control-Allow-Credentials': 'true',
            'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
            'Access-Control-Allow-Headers': 'accept, accept-encoding, access-control-allow-headers, access-control-allow-origin, content-type, accept-language'
        } 
        )
    }

    getDoadores(): Observable<any>{
        return this.httpClient.get<Tecnico>(this.apiUrl);
    }

    /*atualizar(id, data): Observable<any> {
        console.log(JSON.stringify(data));
        return this.httpClient.put<void>(`${this.apiUrl}/${id}`, JSON.stringify(data));
    }*/

    atualizar(id: number, tecnico: Tecnico){        
        return this.httpClient.put(`${this.apiUrl}/${id}`, tecnico)
        .pipe(retry(2),catchError(this.handleError));
    }

    getDoador(id: number): Observable<any>{
        return this.httpClient.get<Tecnico>(this.apiUrl + "/" + id);
    }

    delete(id: number): Observable<any> {
        return this.httpClient.delete(`${this.apiUrl}/${id}`);
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

