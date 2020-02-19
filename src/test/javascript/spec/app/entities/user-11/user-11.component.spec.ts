import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { User11Component } from 'app/entities/user-11/user-11.component';
import { User11Service } from 'app/entities/user-11/user-11.service';
import { User11 } from 'app/shared/model/user-11.model';

describe('Component Tests', () => {
  describe('User11 Management Component', () => {
    let comp: User11Component;
    let fixture: ComponentFixture<User11Component>;
    let service: User11Service;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [User11Component]
      })
        .overrideTemplate(User11Component, '')
        .compileComponents();

      fixture = TestBed.createComponent(User11Component);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(User11Service);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new User11(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.user11S && comp.user11S[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
