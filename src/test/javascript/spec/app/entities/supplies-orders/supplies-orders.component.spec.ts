import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { SuppliesOrdersComponent } from 'app/entities/supplies-orders/supplies-orders.component';
import { SuppliesOrdersService } from 'app/entities/supplies-orders/supplies-orders.service';
import { SuppliesOrders } from 'app/shared/model/supplies-orders.model';

describe('Component Tests', () => {
  describe('SuppliesOrders Management Component', () => {
    let comp: SuppliesOrdersComponent;
    let fixture: ComponentFixture<SuppliesOrdersComponent>;
    let service: SuppliesOrdersService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [SuppliesOrdersComponent]
      })
        .overrideTemplate(SuppliesOrdersComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SuppliesOrdersComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SuppliesOrdersService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SuppliesOrders(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.suppliesOrders && comp.suppliesOrders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
