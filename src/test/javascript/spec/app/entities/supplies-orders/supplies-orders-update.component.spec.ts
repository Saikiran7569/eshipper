import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { SuppliesOrdersUpdateComponent } from 'app/entities/supplies-orders/supplies-orders-update.component';
import { SuppliesOrdersService } from 'app/entities/supplies-orders/supplies-orders.service';
import { SuppliesOrders } from 'app/shared/model/supplies-orders.model';

describe('Component Tests', () => {
  describe('SuppliesOrders Management Update Component', () => {
    let comp: SuppliesOrdersUpdateComponent;
    let fixture: ComponentFixture<SuppliesOrdersUpdateComponent>;
    let service: SuppliesOrdersService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [SuppliesOrdersUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SuppliesOrdersUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SuppliesOrdersUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SuppliesOrdersService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SuppliesOrders(123);
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
        const entity = new SuppliesOrders();
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
