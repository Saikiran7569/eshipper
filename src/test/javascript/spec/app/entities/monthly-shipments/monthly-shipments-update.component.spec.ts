import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { MonthlyShipmentsUpdateComponent } from 'app/entities/monthly-shipments/monthly-shipments-update.component';
import { MonthlyShipmentsService } from 'app/entities/monthly-shipments/monthly-shipments.service';
import { MonthlyShipments } from 'app/shared/model/monthly-shipments.model';

describe('Component Tests', () => {
  describe('MonthlyShipments Management Update Component', () => {
    let comp: MonthlyShipmentsUpdateComponent;
    let fixture: ComponentFixture<MonthlyShipmentsUpdateComponent>;
    let service: MonthlyShipmentsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [MonthlyShipmentsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MonthlyShipmentsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MonthlyShipmentsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MonthlyShipmentsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MonthlyShipments(123);
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
        const entity = new MonthlyShipments();
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
