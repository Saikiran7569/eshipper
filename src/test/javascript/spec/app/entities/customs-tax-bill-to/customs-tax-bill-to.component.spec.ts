import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { CustomsTaxBillToComponent } from 'app/entities/customs-tax-bill-to/customs-tax-bill-to.component';
import { CustomsTaxBillToService } from 'app/entities/customs-tax-bill-to/customs-tax-bill-to.service';
import { CustomsTaxBillTo } from 'app/shared/model/customs-tax-bill-to.model';

describe('Component Tests', () => {
  describe('CustomsTaxBillTo Management Component', () => {
    let comp: CustomsTaxBillToComponent;
    let fixture: ComponentFixture<CustomsTaxBillToComponent>;
    let service: CustomsTaxBillToService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CustomsTaxBillToComponent],
        providers: []
      })
        .overrideTemplate(CustomsTaxBillToComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustomsTaxBillToComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomsTaxBillToService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CustomsTaxBillTo(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.customsTaxBillTos && comp.customsTaxBillTos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
