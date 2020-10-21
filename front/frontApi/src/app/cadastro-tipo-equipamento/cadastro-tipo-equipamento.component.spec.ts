import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroTipoEquipamentoComponent } from './cadastro-tipo-equipamento.component';

describe('CadastroTipoEquipamentoComponent', () => {
  let component: CadastroTipoEquipamentoComponent;
  let fixture: ComponentFixture<CadastroTipoEquipamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastroTipoEquipamentoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroTipoEquipamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
