import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroDoUsuarioComponent } from './cadastro-do-usuario.component';

describe('CadastroDoUsuarioComponent', () => {
  let component: CadastroDoUsuarioComponent;
  let fixture: ComponentFixture<CadastroDoUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastroDoUsuarioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroDoUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
