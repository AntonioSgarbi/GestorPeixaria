import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdutoPesquisaComponent } from './produto-pesquisa.component';

describe('ProdutoPesquisaComponent', () => {
  let component: ProdutoPesquisaComponent;
  let fixture: ComponentFixture<ProdutoPesquisaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProdutoPesquisaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProdutoPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
