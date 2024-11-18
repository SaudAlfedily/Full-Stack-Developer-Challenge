import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NzDemoFormLayoutComponent } from './nz-demo-form-layout.component';

describe('NzDemoFormLayoutComponent', () => {
  let component: NzDemoFormLayoutComponent;
  let fixture: ComponentFixture<NzDemoFormLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NzDemoFormLayoutComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NzDemoFormLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
