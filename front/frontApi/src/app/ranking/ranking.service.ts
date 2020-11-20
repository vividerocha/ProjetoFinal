import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Ranking } from './ranking';
import { Equipamento } from './../cadastro-de-equipamento/equipamento';

@Injectable({
    providedIn: 'root'
})

export class RankingService {

    apiUrl = environment.URLSERVIDOR + "questionario";
    //http://localhost:8081/historicoEquipamento/QuadroDoador/

    constructor(private httpClient: HttpClient) { }

    // Headers
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    getRanking(regiao: string): Observable<any>{
        return this.httpClient.get<Ranking>(this.apiUrl + "/ranking/" + regiao);
    }

    getEquipamentos(regiao: string): Observable<any>{
        return this.httpClient.get<Equipamento>( environment.URLSERVIDOR + "historicoEquipamento" + "/Ranking/" + regiao);
    }

}