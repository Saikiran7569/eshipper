import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommodityType } from 'app/shared/model/commodity-type.model';
import { CommodityTypeService } from './commodity-type.service';

@Component({
  templateUrl: './commodity-type-delete-dialog.component.html',
})
export class CommodityTypeDeleteDialogComponent {
  commodityType?: ICommodityType;

  constructor(
    protected commodityTypeService: CommodityTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.commodityTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('commodityTypeListModification');
      this.activeModal.close();
    });
  }
}
