import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginView} from './login.view';

describe('LoginComponent', () => {
  let component: LoginView;
  let fixture: ComponentFixture<LoginView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginView]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
