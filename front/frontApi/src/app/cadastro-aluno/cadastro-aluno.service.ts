import { Aluno } from './aluno';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { CadastroAlunoComponent } from './cadastro-aluno.component';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';


@Injectable({
    providedIn: 'root'
})
export class AlunoService {

    apiUrl = environment.URLSERVIDOR + "alunos";

    constructor(private httpClient: HttpClient) { }

    // Headers
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    salvar(novoAluno: any): Observable<CadastroAlunoComponent>{
        console.log(novoAluno);
        return this.httpClient.post<CadastroAlunoComponent>(this.apiUrl, JSON.stringify(novoAluno), this.httpOptions)
        .pipe(retry(2),catchError(this.handleError))
        //return this.httpClient.post(this.apiUrl, novoUser);
        
    }

    atualizar(id: number, aluno: any){        
        return this.httpClient.put(`${this.apiUrl}/${id}`, aluno)
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

    getTiposEquipamentos(): Observable<any> {
        return this.httpClient.get<any>(environment.URLSERVIDOR + "tipoEquipamentos");
      }

      getAlunoPorIduser(id: number): Observable<any>{
        return this.httpClient.get<Aluno>(this.apiUrl + "/user/" + id);
    }

}