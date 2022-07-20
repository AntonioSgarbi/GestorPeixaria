import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchStockView } from './search-stock.view';

describe('PesquisaComponent', () => {
  let component: SearchStockView;
  let fixture: ComponentFixture<SearchStockView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchStockView ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchStockView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
