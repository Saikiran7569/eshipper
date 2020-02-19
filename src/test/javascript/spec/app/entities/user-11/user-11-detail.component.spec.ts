import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { User11DetailComponent } from 'app/entities/user-11/user-11-detail.component';
import { User11 } from 'app/shared/model/user-11.model';

describe('Component Tests', () => {
  describe('User11 Management Detail Component', () => {
    let comp: User11DetailComponent;
    let fixture: ComponentFixture<User11DetailComponent>;
    const route = ({ data: of({ user11: new User11(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [User11DetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(User11DetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(User11DetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load user11 on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.user11).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
