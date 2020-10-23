import { environment } from './../../environments/environment';
import { HttpHeaders } from '@angular/common/http';
import { BaseHttpService } from './../services/http/base-http.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map, mergeMap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root',
})
export class AuthRepository {

    constructor(public http: BaseHttpService) { }
    
    post(usuario: string, senha: string) {
        const body = `username=${usuario}&password=${senha}&grant_type=password`;
        const headers = new HttpHeaders({
          'Content-Type':'application/x-www-form-urlencoded',
          'Authorization':'Basic ZnJvbnRlbmQtY2xpZW50OmRpZ2l0YWw=' });
        
         return this.http
            .post(`${environment.URLSERVIDOR}oauth/token`, body, false, false, headers);
    }

    postRefreshToken() {
        const body = 'grant_type=refresh_token';
        const headers = new HttpHeaders({
            'Content-Type':'application/x-www-form-urlencoded',
            'Authorization':'Basic ZnJvbnRlbmQtY2xpZW50OmRpZ2l0YWw=' });
        return this.http
            .post(`${environment.URLSERVIDOR}oauth/token`, body, false, false, headers);
    }

    postCheckToken() {
        const body = `token=${localStorage.getItem("token")}`;
        const headers = new HttpHeaders({
            'Content-Type':'application/x-www-form-urlencoded'});
        return this.http
            .post(`${environment.URLSERVIDOR}oauth/check_token`, body, false, false, headers);
    }

    postLogout() {
        return this.http
            .deleteToken(`${environment.URLSERVIDOR}token/revoke`);
    }
    
}
