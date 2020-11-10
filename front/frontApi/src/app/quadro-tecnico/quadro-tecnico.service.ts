import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { EquipamentosReparo } from './equipamentosReparo';

@Injectable({
    providedIn: 'root'
})

export class QuadroTecnicoService {

    apiUrl = environment.URLSERVIDOR + "historicoEquipamento";
    //http://localhost:8081/historicoEquipamento/QuadroDoador/

    constructor(private httpClient: HttpClient) { }

    // Headers
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    getEquipamentos(id: number): Observable<any>{
        return this.httpClient.get<EquipamentosReparo>(this.apiUrl + "/QuadroTecnico/" + id);
    }
}
