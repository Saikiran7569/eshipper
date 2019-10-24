import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ShipmentPackageDetailComponent } from 'app/entities/shipment-package/shipment-package-detail.component';
import { ShipmentPackage } from 'app/shared/model/shipment-package.model';

describe('Component Tests', () => {
  describe('ShipmentPackage Management Detail Component', () => {
    let comp: ShipmentPackageDetailComponent;
    let fixture: ComponentFixture<ShipmentPackageDetailComponent>;
    const route = ({ data: of({ shipmentPackage: new ShipmentPackage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ShipmentPackageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ShipmentPackageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShipmentPackageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.shipmentPackage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
