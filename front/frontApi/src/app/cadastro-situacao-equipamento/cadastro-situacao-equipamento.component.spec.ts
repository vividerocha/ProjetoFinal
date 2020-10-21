import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroSituacaoEquipamentoComponent } from './cadastro-situacao-equipamento.component';

describe('CadastroSituacaoEquipamentoComponent', () => {
  let component: CadastroSituacaoEquipamentoComponent;
  let fixture: ComponentFixture<CadastroSituacaoEquipamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastroSituacaoEquipamentoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroSituacaoEquipamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
