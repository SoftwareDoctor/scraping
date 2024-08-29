import { TestBed } from '@angular/core/testing';

import { JoblinkService } from './joblink.service';

describe('JoblinkService', () => {
  let service: JoblinkService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JoblinkService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
