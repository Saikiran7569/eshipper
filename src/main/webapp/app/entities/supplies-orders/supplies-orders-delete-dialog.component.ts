import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISuppliesOrders } from 'app/shared/model/supplies-orders.model';
import { SuppliesOrdersService } from './supplies-orders.service';

@Component({
  templateUrl: './supplies-orders-delete-dialog.component.html'
})
export class SuppliesOrdersDeleteDialogComponent {
  suppliesOrders?: ISuppliesOrders;

  constructor(
    protected suppliesOrdersService: SuppliesOrdersService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.suppliesOrdersService.delete(id).subscribe(() => {
      this.eventManager.broadcast('suppliesOrdersListModification');
      this.activeModal.close();
    });
  }
}
