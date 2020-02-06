import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { BatchServiceTypeComponent } from 'app/entities/batch-service-type/batch-service-type.component';
import { BatchServiceTypeService } from 'app/entities/batch-service-type/batch-service-type.service';
import { BatchServiceType } from 'app/shared/model/batch-service-type.model';

describe('Component Tests', () => {
  describe('BatchServiceType Management Component', () => {
    let comp: BatchServiceTypeComponent;
    let fixture: ComponentFixture<BatchServiceTypeComponent>;
    let service: BatchServiceTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [BatchServiceTypeComponent],
        providers: []
      })
        .overrideTemplate(BatchServiceTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BatchServiceTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BatchServiceTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BatchServiceType(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.batchServiceTypes && comp.batchServiceTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
