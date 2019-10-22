import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { PalletTypeDetailComponent } from 'app/entities/pallet-type/pallet-type-detail.component';
import { PalletType } from 'app/shared/model/pallet-type.model';

describe('Component Tests', () => {
  describe('PalletType Management Detail Component', () => {
    let comp: PalletTypeDetailComponent;
    let fixture: ComponentFixture<PalletTypeDetailComponent>;
    const route = ({ data: of({ palletType: new PalletType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [PalletTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PalletTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PalletTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.palletType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
