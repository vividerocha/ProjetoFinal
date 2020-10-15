import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from './login';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formLogin: FormGroup;
  escondeMensagemErro: Boolean;
  

  constructor(private fb: FormBuilder, private router: Router,
    private loginService: LoginService) { }

  ngOnInit(): void {
    this.createForm();
    this.escondeMensagemErro = true;
  }

  createForm() {
    this.formLogin = this.fb.group({
      email: new FormControl(''),
      senha: new FormControl('')
    });
  }
  onSubmit(form: NgForm) {

    if (this.formLogin.valid) {
      console.log('form submitted');
      this.loginService.login(this.formLogin.value.email, this.formLogin.value.senha)
        .subscribe(
          response => {
            console.log(response.id);
            sessionStorage.setItem('isLogado', 'true');
            sessionStorage.setItem('user', response.id);
            this.router.navigate(['/home']);
          },
          error => {
            console.log(error);
            this.escondeMensagemErro = false;
          });

    } else {
      console.log('form invalido');
      this.escondeMensagemErro = false;
    }

  }

}
