import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IShippingOrder } from 'app/shared/model/shipping-order.model';
import { ShippingOrderService } from './shipping-order.service';

@Component({
  templateUrl: './shipping-order-delete-dialog.component.html',
})
export class ShippingOrderDeleteDialogComponent {
  shippingOrder?: IShippingOrder;

  constructor(
    protected shippingOrderService: ShippingOrderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.shippingOrderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('shippingOrderListModification');
      this.activeModal.close();
    });
  }
}
