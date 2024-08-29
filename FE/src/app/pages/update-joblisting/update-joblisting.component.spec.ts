import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateJoblistingComponent } from './update-joblisting.component';

describe('UpdateJoblistingComponent', () => {
  let component: UpdateJoblistingComponent;
  let fixture: ComponentFixture<UpdateJoblistingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateJoblistingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateJoblistingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
