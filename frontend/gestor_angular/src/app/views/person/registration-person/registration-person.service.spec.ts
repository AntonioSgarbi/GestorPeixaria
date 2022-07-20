import { TestBed } from '@angular/core/testing';

import { RegistrationPersonService } from './registration-person.service';

describe('CadastroService', () => {
  let service: RegistrationPersonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegistrationPersonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
