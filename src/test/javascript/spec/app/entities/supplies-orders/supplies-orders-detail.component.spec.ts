import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { SuppliesOrdersDetailComponent } from 'app/entities/supplies-orders/supplies-orders-detail.component';
import { SuppliesOrders } from 'app/shared/model/supplies-orders.model';

describe('Component Tests', () => {
  describe('SuppliesOrders Management Detail Component', () => {
    let comp: SuppliesOrdersDetailComponent;
    let fixture: ComponentFixture<SuppliesOrdersDetailComponent>;
    const route = ({ data: of({ suppliesOrders: new SuppliesOrders(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [SuppliesOrdersDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SuppliesOrdersDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SuppliesOrdersDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load suppliesOrders on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.suppliesOrders).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
