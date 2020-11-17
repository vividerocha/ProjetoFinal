import { Component, NgZone, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { Aluno } from '../cadastro-aluno/aluno';
import { Questionario } from './questionario';
import { QuestionarioService } from './questionario.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-questionario',
  templateUrl: './questionario.component.html',
  styleUrls: ['./questionario.component.css']
})
export class QuestionarioComponent implements OnInit {

  form: FormGroup;
  formularioInvalido: boolean;
  aluno: Aluno;
  idUsuario: number;
  questionarioRespondido: boolean;
  pontuacaoQuestionario: string;

  constructor(private fb: FormBuilder, private service: QuestionarioService,
    private toastService: ToastrService) { }

  ngOnInit(): void {
    this.idUsuario = parseInt(sessionStorage.getItem('idUser'))
    this.formularioInvalido = false;
    this.questionarioRespondido = true;
    this.getQuestionario();
    this.createForm();
    
  }

  createForm(){
    this.form = this.fb.group({
      perg1: new FormControl(null, [Validators.required]),
      perg2: new FormControl(null, [Validators.required]),
      perg3: new FormControl(null, [Validators.required]),
      perg4: new FormControl(null, [Validators.required]),
      perg5: new FormControl(null, [Validators.required]),
      perg6: new FormControl(null, [Validators.required]),
      perg7: new FormControl(null, [Validators.required]),
      perg8: new FormControl(null, [Validators.required]),
      perg9: new FormControl(null, [Validators.required]),
      perg10: new FormControl(null, [Validators.required])
    });
  }

  onSubmit(ngForm: FormGroup){
    if(this.form.valid){
      this.getAluno();
      setTimeout(() => {
          this.formularioInvalido = false;
          const dados = {
            perg1: parseInt(this.form.value.perg1),
            perg2: parseInt(this.form.value.perg2),
            perg3: parseInt(this.form.value.perg3),
            perg4: parseInt(this.form.value.perg4),
            perg5: parseInt(this.form.value.perg5),
            perg6: parseInt(this.form.value.perg6),
            perg7: parseInt(this.form.value.perg7),
            perg8: parseInt(this.form.value.perg8),
            perg9: parseInt(this.form.value.perg9),
            perg10: parseInt(this.form.value.perg10),
            aluno: this.aluno.id,
            pontuacaoTotal: (parseInt(this.form.value.perg1) + parseInt(this.form.value.perg2) + parseInt(this.form.value.perg3) +
                    parseInt(this.form.value.perg4) + parseInt(this.form.value.perg5) + parseInt(this.form.value.perg6) +
                    parseInt(this.form.value.perg7) + parseInt(this.form.value.perg8) + parseInt(this.form.value.perg9) +
                    parseInt(this.form.value.perg10)),
          } as Questionario

          console.log(dados);
          this.service.salvar(dados).subscribe(() => {
            this.toastService.success("Cadastro realizado com Sucesso!");
            this.questionarioRespondido = true;
          });

          
      }, 60);
    }else{
      this.formularioInvalido = true;
    }
  }

  getAluno(){
    this.service.getAluno(sessionStorage.idUser).subscribe(res => {
      this.aluno = res;
      }
    );
  }

  getQuestionario(){
    this.service.getAluno(sessionStorage.idUser).subscribe(res => {
      this.aluno = res;
      setTimeout(() => {
        let id = this.aluno.id;
        this.service.getQuestionario(id).subscribe(res => {
              this.questionarioRespondido = true;
              this.pontuacaoQuestionario = res.pontuacaoTotal;
          },
          error => {
            this.questionarioRespondido = false;
          });
      }, 60);
    }
    );
  }
  

}
