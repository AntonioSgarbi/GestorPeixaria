import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RegistrationPersonView} from './registration-person.view';

describe('CadastroComponent', () => {
  let component: RegistrationPersonView;
  let fixture: ComponentFixture<RegistrationPersonView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegistrationPersonView]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationPersonView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
