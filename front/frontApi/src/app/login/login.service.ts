import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { Login } from './login';
import { Usuario } from './../cadastro-do-usuario/usuario';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})

export class LoginService {

    apiUrl = environment.URLSERVIDOR + "usuarios";
    

    constructor(private httpClient: HttpClient) { }

    login(email: String, senha: String): Observable<any>{
        return this.httpClient.get<Usuario>(this.apiUrl + '/login/' + email + "&" + senha);
    }

    recupera(email: String, nome: String): Observable<any>{
        console.log('service')
        return this.httpClient.get<any>(this.apiUrl + '/rec/' + email + "&" + nome)
        .pipe(retry(1),catchError(this.handleError));
    }

    atualizar(id: number, usuario: any){
        return this.httpClient.put(`${this.apiUrl}/${id}`, usuario)
        .pipe(retry(2),catchError(this.handleError));
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