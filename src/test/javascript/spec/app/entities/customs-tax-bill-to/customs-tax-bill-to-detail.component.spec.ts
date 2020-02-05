import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CustomsTaxBillToDetailComponent } from 'app/entities/customs-tax-bill-to/customs-tax-bill-to-detail.component';
import { CustomsTaxBillTo } from 'app/shared/model/customs-tax-bill-to.model';

describe('Component Tests', () => {
  describe('CustomsTaxBillTo Management Detail Component', () => {
    let comp: CustomsTaxBillToDetailComponent;
    let fixture: ComponentFixture<CustomsTaxBillToDetailComponent>;
    const route = ({ data: of({ customsTaxBillTo: new CustomsTaxBillTo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CustomsTaxBillToDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustomsTaxBillToDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustomsTaxBillToDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load customsTaxBillTo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.customsTaxBillTo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
