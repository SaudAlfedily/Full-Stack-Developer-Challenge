import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleShowromComponent } from './single-showrom.component';

describe('SingleShowromComponent', () => {
  let component: SingleShowromComponent;
  let fixture: ComponentFixture<SingleShowromComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SingleShowromComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SingleShowromComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
