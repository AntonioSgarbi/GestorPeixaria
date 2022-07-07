import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstoquePesquisaComponent } from './estoque-pesquisa.component';

describe('PesquisaComponent', () => {
  let component: EstoquePesquisaComponent;
  let fixture: ComponentFixture<EstoquePesquisaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EstoquePesquisaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EstoquePesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
