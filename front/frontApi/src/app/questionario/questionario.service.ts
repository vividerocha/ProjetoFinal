import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Aluno } from '../cadastro-aluno/aluno';

@Injectable({
    providedIn: 'root'
})

export class QuestionarioService {

    apiUrl = environment.URLSERVIDOR + "questionario";

    constructor(private httpClient: HttpClient) { }

    // Headers
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
     }

    salvar(questionario: any): Observable<any>{
        return this.httpClient.post<any>(this.apiUrl, JSON.stringify(questionario), this.httpOptions)
        .pipe(retry(2),catchError(this.handleError))   
    }

    getAluno(id: number): Observable<any>{
        return this.httpClient.get<Aluno>(environment.URLSERVIDOR + "alunos/user/" + id);
    }

    getQuestionario(id: number): Observable<any>{
        return this.httpClient.get<Aluno>(this.apiUrl + "/aluno/" + id);
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