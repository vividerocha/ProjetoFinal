import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { EquipamentoService } from './cadastro-de-equipamento.service';
import { ToastService } from './../toast/toast.service';
import { Equipamento } from './equipamento';

@Component({
  selector: 'app-cadastro-de-equipamento',
  templateUrl: './cadastro-de-equipamento.component.html',
  styleUrls: ['./cadastro-de-equipamento.component.css']
})
export class CadastroDeEquipamentoComponent implements OnInit {
  tiposEquipamentos;
  formEquipamento: FormGroup;
  formularioInvalido: boolean;

  constructor(private equipamentoService: EquipamentoService,
    private fb: FormBuilder, private toastService: ToastService) { }

  ngOnInit(): void {
    this.criaForm();
    this.getTiposEquipamentos();
  }

  getTiposEquipamentos() {
    this.equipamentoService.getTiposEquipamentos().subscribe(data => {
      this.tiposEquipamentos = data;
    });
  }
  criaForm(){
    this.formEquipamento = this.fb.group({
      tiposEquipamentos : new FormControl(''),
      funcionando: new FormControl(''),
      descricao: new FormControl(null, [Validators.required])
    });
  }

  onSubmit(form: NgForm){
    let func;
    if(this.formEquipamento.value.funcionando == "Sim"){
      func = true;
    }else{
      func = false;
    }
    const dados = {
      descricao: this.formEquipamento.value.descricao,
      funcionando: func,
      tipoEquipamento: this.formEquipamento.value.tiposEquipamentos
    } as Equipamento;

    console.log(dados);
    if(this.formEquipamento.valid){
        this.equipamentoService.salvar(dados)
        .subscribe(
          response => {
            console.log(response);            
            this.showSuccess("Cadastro realizado com Sucesso!");
          },
          error => {
            console.log(error);
            alert(error.error);
          });
    }else{
      this.formularioInvalido = true;
    }
    

  }


  get funcionando(){
    return this.formEquipamento.get('funcionando');
  }

  get descricao(){
    return this.formEquipamento.get('descricao');
  }

  showSuccess(mensagem: string) {
    this.toastService.show(mensagem, {
      classname: 'bg-successToast',
      delay: 2000 ,
      autohide: true,
      headertext: 'Toast Header'
    });
  }
}
