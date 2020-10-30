import { MenuComponent } from './../menu/menu.component';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthRepository } from './auth-repository';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  jwtPayload: any; 

  constructor(public repository: AuthRepository, private router: Router) { 
    this.carregarToken();
  }

  login(usuario: string, senha: string){
    
    return this.repository.post(usuario, senha).subscribe(resposta => {
        
        const json: JSON = JSON.parse(JSON.stringify(resposta));
        sessionStorage.setItem('idUserLogado', json['data']['usuario_id'])        
        this.armazenarToken(json['data']['access_token']);       
        location.reload();
        console.log('Novo access token criado! '+JSON.stringify(this.jwtPayload));
        this.router.navigate(['/home']);
      },
        (e) => {
          console.log(e.error.error_description);      
        });    
  }

  private armazenarToken(token: string) {
    this.jwtPayload = JSON.parse(atob(token.split('.')[1]));    
    sessionStorage.setItem('token', token);
    
  }

  private carregarToken() {
    const token = sessionStorage.getItem('token');

    if (token) {
      this.armazenarToken(token);
    }
  }

  logout() {
    return this.repository.postLogout().subscribe(resposta => {
        this.limparAccessToken();
        this.router.navigate(['/']);
      },
      (e) => {
        console.log(e.error.error_description);      
      }); 
  }

  limparAccessToken() {
    sessionStorage.removeItem('token');
    this.jwtPayload = null;
  }

  isAccessTokenInvalido() {
    const token = sessionStorage.getItem('token');

    return !token;// || this.isTokenExpired();
  }

  isTokenExpired(){

    this.repository.postCheckToken().subscribe(resposta => {
        
      const json: JSON = JSON.parse(JSON.stringify(resposta));
      
      if(json['data']['active']){
        return true;
      }
    
    });

    return true;
  }

  temPermissao(permissao: string) {
    return this.jwtPayload && this.jwtPayload.authorities.includes(permissao);
  }

  temQualquerPermissao(roles) {
    for (const role of roles) {
      if (this.temPermissao(role)) {
        return true;
      }
    }
    return false;
  }

  obterNovoAccessToken(){    
    return this.repository.postRefreshToken().subscribe(resposta => {
      const json: JSON = JSON.parse(JSON.stringify(resposta));
      this.armazenarToken(json['data']['access_token']);
      console.log('Novo access token criado! '+JSON.stringify(this.jwtPayload));
      },
      (e) => {
        console.log(e.error.error_description);      
      });  
  }

}
