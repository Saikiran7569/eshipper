import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { MonthlyShipmentsComponent } from 'app/entities/monthly-shipments/monthly-shipments.component';
import { MonthlyShipmentsService } from 'app/entities/monthly-shipments/monthly-shipments.service';
import { MonthlyShipments } from 'app/shared/model/monthly-shipments.model';

describe('Component Tests', () => {
  describe('MonthlyShipments Management Component', () => {
    let comp: MonthlyShipmentsComponent;
    let fixture: ComponentFixture<MonthlyShipmentsComponent>;
    let service: MonthlyShipmentsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [MonthlyShipmentsComponent],
        providers: []
      })
        .overrideTemplate(MonthlyShipmentsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MonthlyShipmentsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MonthlyShipmentsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MonthlyShipments(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.monthlyShipments && comp.monthlyShipments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
