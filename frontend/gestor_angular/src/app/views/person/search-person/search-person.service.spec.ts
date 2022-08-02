import { TestBed } from '@angular/core/testing';

import { SearchPersonService } from './search-person.service';

describe('SearchPersonService', () => {
  let service: SearchPersonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchPersonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
