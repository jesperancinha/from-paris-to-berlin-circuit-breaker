import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FptbOverviewComponent } from './fptb-overview.component';

describe('FptbOverviewComponent', () => {
  let component: FptbOverviewComponent;
  let fixture: ComponentFixture<FptbOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FptbOverviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FptbOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
