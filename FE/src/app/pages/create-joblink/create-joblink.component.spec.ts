import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateJoblinkComponent } from './create-joblink.component';

describe('CreateJoblinkComponent', () => {
  let component: CreateJoblinkComponent;
  let fixture: ComponentFixture<CreateJoblinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateJoblinkComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateJoblinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
