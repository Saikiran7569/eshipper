import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { PalletTypeUpdateComponent } from 'app/entities/pallet-type/pallet-type-update.component';
import { PalletTypeService } from 'app/entities/pallet-type/pallet-type.service';
import { PalletType } from 'app/shared/model/pallet-type.model';

describe('Component Tests', () => {
  describe('PalletType Management Update Component', () => {
    let comp: PalletTypeUpdateComponent;
    let fixture: ComponentFixture<PalletTypeUpdateComponent>;
    let service: PalletTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [PalletTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PalletTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PalletTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PalletTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PalletType(123);
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
        const entity = new PalletType();
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
