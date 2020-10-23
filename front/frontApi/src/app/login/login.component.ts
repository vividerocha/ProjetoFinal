import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from './login';
import { AuthService } from './../seguranca/auth.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formLogin: FormGroup;
  escondeMensagemErro: Boolean;


  constructor(private fb: FormBuilder, private router: Router,
    private service: AuthService) { }

  ngOnInit(): void {  
    this.createForm();
    this.escondeMensagemErro = true;  
    if (localStorage.getItem('token') !== null) {
      this.router.navigate(['/home']);
    }

  }

  createForm() {
    this.formLogin = this.fb.group({
      email: new FormControl(''),
      senha: new FormControl('')
    });
  }
  onSubmit(form: NgForm) {
    if (this.formLogin.invalid) {
      return;
    }
    //fazer a chamada  ao service
    this.service.login(this.formLogin.value.email, this.formLogin.value.senha);

  }

}
