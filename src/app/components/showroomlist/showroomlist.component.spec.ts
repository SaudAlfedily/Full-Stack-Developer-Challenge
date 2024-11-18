import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowroomlistComponent } from './showroomlist.component';

describe('ShowroomlistComponent', () => {
  let component: ShowroomlistComponent;
  let fixture: ComponentFixture<ShowroomlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShowroomlistComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ShowroomlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
