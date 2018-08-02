import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MobileNewComponent } from './mobile-new.component';

describe('MobileNewComponent', () => {
  let component: MobileNewComponent;
  let fixture: ComponentFixture<MobileNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MobileNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MobileNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
