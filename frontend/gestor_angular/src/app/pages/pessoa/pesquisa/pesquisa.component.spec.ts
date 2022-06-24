import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PessoaPesquisaComponent} from './pesquisa.component';

describe('PesquisaComponent', () => {
  let component: PessoaPesquisaComponent;
  let fixture: ComponentFixture<PessoaPesquisaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PessoaPesquisaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PessoaPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
