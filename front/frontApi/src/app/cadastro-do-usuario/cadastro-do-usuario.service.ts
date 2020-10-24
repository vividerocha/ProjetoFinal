import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { Usuario } from './usuario';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';



@Injectable({
    providedIn: 'root'
})

export class CadastroDoUsuarioService {

    apiUrl = environment.URLSERVIDOR + "usuarios";
    

    constructor(private httpClient: HttpClient) { }

    // Headers
    //httpOptions = {
    //    headers: new HttpHeaders({ 'Content-Type': 'application/json' }), responseType: 'text' as 'json' 
    // }

     salvar(usuario: Usuario): Observable<Usuario> {
        return this.httpClient.post<Usuario>(this.apiUrl, usuario);
      }

    verificaEmail(email: String): Observable<any>{
        return this.httpClient.get<Usuario>(this.apiUrl + '/email/' + email);
    }

    verificaUser(user: String): Observable<any>{
        return this.httpClient.get<Usuario>(this.apiUrl + '/user/' + user);
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