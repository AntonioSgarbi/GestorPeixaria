import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchProductView } from './search-product.view';

describe('ProdutoPesquisaComponent', () => {
  let component: SearchProductView;
  let fixture: ComponentFixture<SearchProductView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchProductView ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchProductView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
