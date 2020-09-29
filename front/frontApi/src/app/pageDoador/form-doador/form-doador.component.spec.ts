import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormDoadorComponent } from './form-doador.component';

describe('FormDoadorComponent', () => {
  let component: FormDoadorComponent;
  let fixture: ComponentFixture<FormDoadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormDoadorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormDoadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
