import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { BoxPackageTypeUpdateComponent } from 'app/entities/box-package-type/box-package-type-update.component';
import { BoxPackageTypeService } from 'app/entities/box-package-type/box-package-type.service';
import { BoxPackageType } from 'app/shared/model/box-package-type.model';

describe('Component Tests', () => {
  describe('BoxPackageType Management Update Component', () => {
    let comp: BoxPackageTypeUpdateComponent;
    let fixture: ComponentFixture<BoxPackageTypeUpdateComponent>;
    let service: BoxPackageTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [BoxPackageTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BoxPackageTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BoxPackageTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BoxPackageTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BoxPackageType(123);
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
        const entity = new BoxPackageType();
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
