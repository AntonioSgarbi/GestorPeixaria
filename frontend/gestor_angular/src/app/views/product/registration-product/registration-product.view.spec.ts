import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RegistrationProductView} from './registration-product.view';

describe('CadastroComponent', () => {
  let component: RegistrationProductView;
  let fixture: ComponentFixture<RegistrationProductView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationProductView ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationProductView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
