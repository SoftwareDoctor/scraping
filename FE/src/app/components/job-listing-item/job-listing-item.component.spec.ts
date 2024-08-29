import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobListingItemComponent } from './job-listing-item.component';

describe('JobListingItemComponent', () => {
  let component: JobListingItemComponent;
  let fixture: ComponentFixture<JobListingItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JobListingItemComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JobListingItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
