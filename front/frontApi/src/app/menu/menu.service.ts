import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { Usuario } from '../cadastro-do-usuario/usuario';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';



@Injectable({
    providedIn: 'root'
})

export class MenuService {

    apiUrl = environment.URLSERVIDOR + "usuarios";
    

    constructor(private httpClient: HttpClient) { }
    

    getUsuario(id: String): Observable<any>{
        return this.httpClient.get<Usuario>(this.apiUrl + '/' + id);
    }

}