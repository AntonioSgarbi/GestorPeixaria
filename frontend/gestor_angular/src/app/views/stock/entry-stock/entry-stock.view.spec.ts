import {ComponentFixture, TestBed} from '@angular/core/testing';

import {EntryStockView} from './entry-stock.view';

describe('EntradaComponent', () => {
  let component: EntryStockView;
  let fixture: ComponentFixture<EntryStockView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntryStockView ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EntryStockView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
