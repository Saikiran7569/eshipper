import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EshipperTestModule } from '../../../test.module';
import { ShipmentPackageDeleteDialogComponent } from 'app/entities/shipment-package/shipment-package-delete-dialog.component';
import { ShipmentPackageService } from 'app/entities/shipment-package/shipment-package.service';

describe('Component Tests', () => {
  describe('ShipmentPackage Management Delete Component', () => {
    let comp: ShipmentPackageDeleteDialogComponent;
    let fixture: ComponentFixture<ShipmentPackageDeleteDialogComponent>;
    let service: ShipmentPackageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ShipmentPackageDeleteDialogComponent]
      })
        .overrideTemplate(ShipmentPackageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShipmentPackageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShipmentPackageService);
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
