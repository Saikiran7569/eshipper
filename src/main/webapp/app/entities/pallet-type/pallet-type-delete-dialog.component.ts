import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPalletType } from 'app/shared/model/pallet-type.model';
import { PalletTypeService } from './pallet-type.service';

@Component({
  selector: 'jhi-pallet-type-delete-dialog',
  templateUrl: './pallet-type-delete-dialog.component.html'
})
export class PalletTypeDeleteDialogComponent {
  palletType: IPalletType;

  constructor(
    protected palletTypeService: PalletTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.palletTypeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'palletTypeListModification',
        content: 'Deleted an palletType'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pallet-type-delete-popup',
  template: ''
})
export class PalletTypeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ palletType }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PalletTypeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.palletType = palletType;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/pallet-type', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/pallet-type', { outlets: { popup: null } }]);
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
