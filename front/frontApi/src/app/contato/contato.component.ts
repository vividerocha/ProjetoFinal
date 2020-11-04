import { Component, OnInit, ɵConsole } from '@angular/core';
import { FormBuilder, FormGroup, FormControl,  NgForm, Validators } from '@angular/forms';
import { ContatoService } from './contato.service';
import { ToastrService } from 'ngx-toastr';
import { Mensagem } from './mensagem';

@Component({
  selector: 'app-contato',
  templateUrl: './contato.component.html',
  styleUrls: ['./contato.component.css']
})
export class ContatoComponent implements OnInit {

  form: FormGroup;
  formularioInvalido: boolean;
  habilita: boolean;

  constructor(private service: ContatoService,
    private fb: FormBuilder,
    private toastService: ToastrService) { }

  ngOnInit(): void {
    this.createForm();
    this.habilita = false;
    this.formularioInvalido = false;
  }

  onSubmit(form: NgForm){
    let mensagemFormatada = "Remetente: " + this.form.get('nome').value + "<br>" +
            "Telefone: " + this.form.get('telefone').value + "<br>" +
            "Mensagem: " + this.form.get('mensagem').value;

    const dados = {
      destinatario: "doaju.dh.projeto@gmail.com",
	    assunto: this.form.get('assunto').value,
      mensagem: mensagemFormatada
    } as Mensagem;

    console.log(dados);
    if(this.form.valid){
      this.habilita = false;
      this.service.enviarEmail(dados).subscribe(() => {
        this.toastService.success("Email enviado com Sucesso!");
        this.form.reset();
      });
    }else{
      this.habilita = true;
      this.formularioInvalido = true;
    }  
  }

  createForm() {
    this.form = this.fb.group({
      nome: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      telefone: new FormControl(null, [Validators.required, Validators.minLength(10)]),
      email: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      assunto: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      mensagem: new FormControl(null, [Validators.required, Validators.minLength(4)])
    });

  }
}