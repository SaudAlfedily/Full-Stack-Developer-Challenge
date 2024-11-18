import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateShowromComponent } from './update-showrom.component';

describe('UpdateShowromComponent', () => {
  let component: UpdateShowromComponent;
  let fixture: ComponentFixture<UpdateShowromComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateShowromComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateShowromComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
