import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { IndustryDetailComponent } from 'app/entities/industry/industry-detail.component';
import { Industry } from 'app/shared/model/industry.model';

describe('Component Tests', () => {
  describe('Industry Management Detail Component', () => {
    let comp: IndustryDetailComponent;
    let fixture: ComponentFixture<IndustryDetailComponent>;
    const route = ({ data: of({ industry: new Industry(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [IndustryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(IndustryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(IndustryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load industry on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.industry).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
