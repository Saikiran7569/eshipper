import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { User11UpdateComponent } from 'app/entities/user-11/user-11-update.component';
import { User11Service } from 'app/entities/user-11/user-11.service';
import { User11 } from 'app/shared/model/user-11.model';

describe('Component Tests', () => {
  describe('User11 Management Update Component', () => {
    let comp: User11UpdateComponent;
    let fixture: ComponentFixture<User11UpdateComponent>;
    let service: User11Service;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [User11UpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(User11UpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(User11UpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(User11Service);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new User11(123);
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
        const entity = new User11();
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
