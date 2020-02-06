import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { BatchServiceTypeUpdateComponent } from 'app/entities/batch-service-type/batch-service-type-update.component';
import { BatchServiceTypeService } from 'app/entities/batch-service-type/batch-service-type.service';
import { BatchServiceType } from 'app/shared/model/batch-service-type.model';

describe('Component Tests', () => {
  describe('BatchServiceType Management Update Component', () => {
    let comp: BatchServiceTypeUpdateComponent;
    let fixture: ComponentFixture<BatchServiceTypeUpdateComponent>;
    let service: BatchServiceTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [BatchServiceTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BatchServiceTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BatchServiceTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BatchServiceTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BatchServiceType(123);
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
        const entity = new BatchServiceType();
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
