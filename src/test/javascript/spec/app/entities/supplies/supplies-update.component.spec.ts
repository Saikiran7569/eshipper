import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { SuppliesUpdateComponent } from 'app/entities/supplies/supplies-update.component';
import { SuppliesService } from 'app/entities/supplies/supplies.service';
import { Supplies } from 'app/shared/model/supplies.model';

describe('Component Tests', () => {
  describe('Supplies Management Update Component', () => {
    let comp: SuppliesUpdateComponent;
    let fixture: ComponentFixture<SuppliesUpdateComponent>;
    let service: SuppliesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [SuppliesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SuppliesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SuppliesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SuppliesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Supplies(123);
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
        const entity = new Supplies();
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
