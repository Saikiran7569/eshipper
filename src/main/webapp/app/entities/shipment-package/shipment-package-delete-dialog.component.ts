import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IShipmentPackage } from 'app/shared/model/shipment-package.model';
import { ShipmentPackageService } from './shipment-package.service';

@Component({
  selector: 'jhi-shipment-package-delete-dialog',
  templateUrl: './shipment-package-delete-dialog.component.html'
})
export class ShipmentPackageDeleteDialogComponent {
  shipmentPackage: IShipmentPackage;

  constructor(
    protected shipmentPackageService: ShipmentPackageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.shipmentPackageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'shipmentPackageListModification',
        content: 'Deleted an shipmentPackage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-shipment-package-delete-popup',
  template: ''
})
export class ShipmentPackageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ shipmentPackage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ShipmentPackageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.shipmentPackage = shipmentPackage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/shipment-package', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/shipment-package', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
