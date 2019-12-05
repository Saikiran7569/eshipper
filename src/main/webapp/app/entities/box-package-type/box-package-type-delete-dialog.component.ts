import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBoxPackageType } from 'app/shared/model/box-package-type.model';
import { BoxPackageTypeService } from './box-package-type.service';

@Component({
  templateUrl: './box-package-type-delete-dialog.component.html'
})
export class BoxPackageTypeDeleteDialogComponent {
  boxPackageType: IBoxPackageType;

  constructor(
    protected boxPackageTypeService: BoxPackageTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.boxPackageTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'boxPackageTypeListModification',
        content: 'Deleted an boxPackageType'
      });
      this.activeModal.dismiss(true);
    });
  }
}
