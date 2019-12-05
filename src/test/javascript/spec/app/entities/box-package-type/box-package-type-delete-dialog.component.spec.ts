import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EshipperTestModule } from '../../../test.module';
import { BoxPackageTypeDeleteDialogComponent } from 'app/entities/box-package-type/box-package-type-delete-dialog.component';
import { BoxPackageTypeService } from 'app/entities/box-package-type/box-package-type.service';

describe('Component Tests', () => {
  describe('BoxPackageType Management Delete Component', () => {
    let comp: BoxPackageTypeDeleteDialogComponent;
    let fixture: ComponentFixture<BoxPackageTypeDeleteDialogComponent>;
    let service: BoxPackageTypeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [BoxPackageTypeDeleteDialogComponent]
      })
        .overrideTemplate(BoxPackageTypeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BoxPackageTypeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BoxPackageTypeService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
