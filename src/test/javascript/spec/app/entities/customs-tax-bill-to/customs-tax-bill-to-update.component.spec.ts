import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CustomsTaxBillToUpdateComponent } from 'app/entities/customs-tax-bill-to/customs-tax-bill-to-update.component';
import { CustomsTaxBillToService } from 'app/entities/customs-tax-bill-to/customs-tax-bill-to.service';
import { CustomsTaxBillTo } from 'app/shared/model/customs-tax-bill-to.model';

describe('Component Tests', () => {
  describe('CustomsTaxBillTo Management Update Component', () => {
    let comp: CustomsTaxBillToUpdateComponent;
    let fixture: ComponentFixture<CustomsTaxBillToUpdateComponent>;
    let service: CustomsTaxBillToService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CustomsTaxBillToUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustomsTaxBillToUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustomsTaxBillToUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomsTaxBillToService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustomsTaxBillTo(123);
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
        const entity = new CustomsTaxBillTo();
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
