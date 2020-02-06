import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { BatchServiceTypeDetailComponent } from 'app/entities/batch-service-type/batch-service-type-detail.component';
import { BatchServiceType } from 'app/shared/model/batch-service-type.model';

describe('Component Tests', () => {
  describe('BatchServiceType Management Detail Component', () => {
    let comp: BatchServiceTypeDetailComponent;
    let fixture: ComponentFixture<BatchServiceTypeDetailComponent>;
    const route = ({ data: of({ batchServiceType: new BatchServiceType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [BatchServiceTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BatchServiceTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BatchServiceTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load batchServiceType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.batchServiceType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
