import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ShipmentPackageUpdateComponent } from 'app/entities/shipment-package/shipment-package-update.component';
import { ShipmentPackageService } from 'app/entities/shipment-package/shipment-package.service';
import { ShipmentPackage } from 'app/shared/model/shipment-package.model';

describe('Component Tests', () => {
  describe('ShipmentPackage Management Update Component', () => {
    let comp: ShipmentPackageUpdateComponent;
    let fixture: ComponentFixture<ShipmentPackageUpdateComponent>;
    let service: ShipmentPackageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ShipmentPackageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ShipmentPackageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShipmentPackageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShipmentPackageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ShipmentPackage(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ShipmentPackage();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
