import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { SuppliesDetailComponent } from 'app/entities/supplies/supplies-detail.component';
import { Supplies } from 'app/shared/model/supplies.model';

describe('Component Tests', () => {
  describe('Supplies Management Detail Component', () => {
    let comp: SuppliesDetailComponent;
    let fixture: ComponentFixture<SuppliesDetailComponent>;
    const route = ({ data: of({ supplies: new Supplies(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [SuppliesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SuppliesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SuppliesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load supplies on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.supplies).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
