import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { PalletTypeComponent } from 'app/entities/pallet-type/pallet-type.component';
import { PalletTypeService } from 'app/entities/pallet-type/pallet-type.service';
import { PalletType } from 'app/shared/model/pallet-type.model';

describe('Component Tests', () => {
  describe('PalletType Management Component', () => {
    let comp: PalletTypeComponent;
    let fixture: ComponentFixture<PalletTypeComponent>;
    let service: PalletTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [PalletTypeComponent],
        providers: []
      })
        .overrideTemplate(PalletTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PalletTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PalletTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PalletType(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.palletTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
