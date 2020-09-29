import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroDeEquipamentoComponent } from './cadastro-de-equipamento.component';

describe('CadastroDeEquipamentoComponent', () => {
  let component: CadastroDeEquipamentoComponent;
  let fixture: ComponentFixture<CadastroDeEquipamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastroDeEquipamentoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroDeEquipamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
