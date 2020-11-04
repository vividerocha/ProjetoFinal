import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuadroUsuarioComponent } from './quadro-usuario.component';

describe('QuadroUsuarioComponent', () => {
  let component: QuadroUsuarioComponent;
  let fixture: ComponentFixture<QuadroUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuadroUsuarioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuadroUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
