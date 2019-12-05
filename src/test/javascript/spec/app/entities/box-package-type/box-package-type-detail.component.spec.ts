import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { BoxPackageTypeDetailComponent } from 'app/entities/box-package-type/box-package-type-detail.component';
import { BoxPackageType } from 'app/shared/model/box-package-type.model';

describe('Component Tests', () => {
  describe('BoxPackageType Management Detail Component', () => {
    let comp: BoxPackageTypeDetailComponent;
    let fixture: ComponentFixture<BoxPackageTypeDetailComponent>;
    const route = ({ data: of({ boxPackageType: new BoxPackageType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [BoxPackageTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BoxPackageTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BoxPackageTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.boxPackageType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
