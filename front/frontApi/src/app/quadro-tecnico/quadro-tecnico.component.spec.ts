import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuadroTecnicoComponent } from './quadro-tecnico.component';

describe('QuadroTecnicoComponent', () => {
  let component: QuadroTecnicoComponent;
  let fixture: ComponentFixture<QuadroTecnicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuadroTecnicoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuadroTecnicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
