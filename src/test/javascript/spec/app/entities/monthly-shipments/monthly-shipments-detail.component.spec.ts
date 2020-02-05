import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { MonthlyShipmentsDetailComponent } from 'app/entities/monthly-shipments/monthly-shipments-detail.component';
import { MonthlyShipments } from 'app/shared/model/monthly-shipments.model';

describe('Component Tests', () => {
  describe('MonthlyShipments Management Detail Component', () => {
    let comp: MonthlyShipmentsDetailComponent;
    let fixture: ComponentFixture<MonthlyShipmentsDetailComponent>;
    const route = ({ data: of({ monthlyShipments: new MonthlyShipments(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [MonthlyShipmentsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MonthlyShipmentsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MonthlyShipmentsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load monthlyShipments on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.monthlyShipments).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
