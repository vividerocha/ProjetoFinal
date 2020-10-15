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

}