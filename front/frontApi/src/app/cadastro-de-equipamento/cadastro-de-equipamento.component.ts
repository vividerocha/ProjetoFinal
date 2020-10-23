import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, NgForm, Validators } from '@angular/forms';
import { EquipamentoService } from './cadastro-de-equipamento.service';
import { Equipamento } from './equipamento';
import { TipoEquipamento } from '../cadastro-tipo-equipamento/tipoEquipamento';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cadastro-de-equipamento',
  templateUrl: './cadastro-de-equipamento.component.html',
  styleUrls: ['./cadastro-de-equipamento.component.css']
})
export class CadastroDeEquipamentoComponent implements OnInit {
  tiposEquipamentosLista: TipoEquipamento;
  formEquipamento: FormGroup;
  formularioInvalido: boolean;

  constructor(private equipamentoService: EquipamentoService,
    private fb: FormBuilder, private toastService: ToastrService) { }

  ngOnInit(): void {
    this.criaForm();
    this.getTiposEquipamentos();
  }

  getTiposEquipamentos() {
    this.equipamentoService.getTiposEquipamentos().subscribe(data => {
      this.tiposEquipamentosLista = data;
    });
  }
  criaForm(){
    this.formEquipamento = this.fb.group({
      tiposEquipamentos : new FormControl(''),
      funcionando: new FormControl(''),
      descricaoEquipamento: new FormControl(null, [Validators.required])
    });
  }

  onSubmit(form: NgForm){
    let func;
    if(this.formEquipamento.value.funcionando == "true"){
      func = true;
    }else{
      func = false;
    }

    console.log(this.formEquipamento.value.tiposEquipamentos);
    
    const dados = {
      descricaoEquipamento: this.formEquipamento.value.descricaoEquipamento,
      funcionando: func,
      tipoEquipamento: this.formEquipamento.value.tiposEquipamentos
    } as Equipamento;

    //console.log(this.formEquipamento.value);
    console.log(dados);
    if(this.formEquipamento.valid){
        this.equipamentoService.salvar(dados)
        //this.equipamentoService.salvar(this.formEquipamento.value)
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

  get descricaoEquipamento(){
    return this.formEquipamento.get('descricaoEquipamento');
  }

  showSuccess(mensagem: string) {
      this.toastService.success(mensagem);
  }
}
