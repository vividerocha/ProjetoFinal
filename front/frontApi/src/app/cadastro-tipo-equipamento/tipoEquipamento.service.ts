import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { TipoEquipamento } from './tipoEquipamento';

@Injectable({
    providedIn: 'root'
})
export class TipoEquipamentoService {

    apiUrl = environment.URLSERVIDOR + "tipoEquipamentos";

    constructor(private httpClient: HttpClient) { }

    // Headers
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    salvar(novoEquipamento: any): Observable<any>{
        return this.httpClient.post(this.apiUrl, JSON.stringify(novoEquipamento), this.httpOptions)
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
        return this.httpClient.get<any>(this.apiUrl);
    }

    verificaTipo(desc: string): Observable<any>{
        return this.httpClient.get<TipoEquipamento>(this.apiUrl + '/desc/' + desc);
    }

    getTipo(id: number): Observable<any>{
        return this.httpClient.get<TipoEquipamento>(this.apiUrl + "/" + id);
    }

    delete(id: number): Observable<any> {
        return this.httpClient.delete(`${this.apiUrl}/${id}`);
    }
    atualizar(id: number, tipoEquipamento: TipoEquipamento){   
        console.log("atualizando");
        console.log(`${this.apiUrl}/${id}`);
        return this.httpClient.put(`${this.apiUrl}/${id}`, tipoEquipamento)
        .pipe(retry(2),catchError(this.handleError));
    }

}