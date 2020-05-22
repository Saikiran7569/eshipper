import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IShippingOrder } from 'app/shared/model/shipping-order.model';
import { ShippingOrderService } from './shipping-order.service';
import { ShippingOrderDeleteDialogComponent } from './shipping-order-delete-dialog.component';

@Component({
  selector: 'jhi-shipping-order',
  templateUrl: './shipping-order.component.html',
})
export class ShippingOrderComponent implements OnInit, OnDestroy {
  shippingOrders?: IShippingOrder[];
  eventSubscriber?: Subscription;

  constructor(
    protected shippingOrderService: ShippingOrderService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.shippingOrderService.query().subscribe((res: HttpResponse<IShippingOrder[]>) => (this.shippingOrders = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInShippingOrders();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IShippingOrder): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInShippingOrders(): void {
    this.eventSubscriber = this.eventManager.subscribe('shippingOrderListModification', () => this.loadAll());
  }

  delete(shippingOrder: IShippingOrder): void {
    const modalRef = this.modalService.open(ShippingOrderDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.shippingOrder = shippingOrder;
  }
}
