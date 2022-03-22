import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateUserListComponent } from './update-user-list.component';

describe('UpdateUserListComponent', () => {
  let component: UpdateUserListComponent;
  let fixture: ComponentFixture<UpdateUserListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateUserListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateUserListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
