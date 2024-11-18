import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarsInShowroomComponent } from './cars-in-showroom.component';

describe('CarsInShowroomComponent', () => {
  let component: CarsInShowroomComponent;
  let fixture: ComponentFixture<CarsInShowroomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarsInShowroomComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarsInShowroomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
