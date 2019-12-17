import { TestBed } from '@angular/core/testing';

import { MovieRestServiceService } from './movie-rest-service.service';

describe('MovieRestServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MovieRestServiceService = TestBed.get(MovieRestServiceService);
    expect(service).toBeTruthy();
  });
});
