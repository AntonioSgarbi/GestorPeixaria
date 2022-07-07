import {ComponentFixture, TestBed} from '@angular/core/testing';

import {EstoqueEntradaComponent} from './estoque-entrada.component';

describe('EntradaComponent', () => {
  let component: EstoqueEntradaComponent;
  let fixture: ComponentFixture<EstoqueEntradaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EstoqueEntradaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EstoqueEntradaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
