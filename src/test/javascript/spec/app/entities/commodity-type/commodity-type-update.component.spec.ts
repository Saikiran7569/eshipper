import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CommodityTypeUpdateComponent } from 'app/entities/commodity-type/commodity-type-update.component';
import { CommodityTypeService } from 'app/entities/commodity-type/commodity-type.service';
import { CommodityType } from 'app/shared/model/commodity-type.model';

describe('Component Tests', () => {
  describe('CommodityType Management Update Component', () => {
    let comp: CommodityTypeUpdateComponent;
    let fixture: ComponentFixture<CommodityTypeUpdateComponent>;
    let service: CommodityTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CommodityTypeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CommodityTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommodityTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommodityTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommodityType(123);
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
        const entity = new CommodityType();
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
