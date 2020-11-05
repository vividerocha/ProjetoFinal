import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuadroAlunoComponent } from './quadro-aluno.component';

describe('QuadroAlunoComponent', () => {
  let component: QuadroAlunoComponent;
  let fixture: ComponentFixture<QuadroAlunoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuadroAlunoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuadroAlunoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
