import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CommodityTypeDetailComponent } from 'app/entities/commodity-type/commodity-type-detail.component';
import { CommodityType } from 'app/shared/model/commodity-type.model';

describe('Component Tests', () => {
  describe('CommodityType Management Detail Component', () => {
    let comp: CommodityTypeDetailComponent;
    let fixture: ComponentFixture<CommodityTypeDetailComponent>;
    const route = ({ data: of({ commodityType: new CommodityType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CommodityTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CommodityTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CommodityTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load commodityType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.commodityType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
