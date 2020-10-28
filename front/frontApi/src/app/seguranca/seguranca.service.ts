import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { Usuario } from './../cadastro-do-usuario/usuario';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';



@Injectable({
    providedIn: 'root'
})

export class SegurancaService {

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

    buscaIdAdmin(desc: String): Observable<any>{
        return this.httpClient.get(this.apiUrl + '/desc/' + desc);
    }

    verificaUser(user: String): Observable<any>{
        return this.httpClient.get<Usuario>(this.apiUrl + '/user/' + user);
    }

    getUsuarios(): Observable<any>{
        return this.httpClient.get<Usuario>(this.apiUrl);
    }

    atualizar(id: number, usuario: any){        
        console.log(JSON.stringify(usuario));
        return this.httpClient.put(`${this.apiUrl}/${id}`, usuario)
        .pipe(retry(2),catchError(this.handleError));
    }

    getUsuario(id: number): Observable<any>{
        return this.httpClient.get<Usuario>(this.apiUrl + "/" + id);
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