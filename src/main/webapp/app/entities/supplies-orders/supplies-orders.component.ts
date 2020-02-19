import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISuppliesOrders } from 'app/shared/model/supplies-orders.model';
import { SuppliesOrdersService } from './supplies-orders.service';
import { SuppliesOrdersDeleteDialogComponent } from './supplies-orders-delete-dialog.component';

@Component({
  selector: 'jhi-supplies-orders',
  templateUrl: './supplies-orders.component.html'
})
export class SuppliesOrdersComponent implements OnInit, OnDestroy {
  suppliesOrders?: ISuppliesOrders[];
  eventSubscriber?: Subscription;

  constructor(
    protected suppliesOrdersService: SuppliesOrdersService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.suppliesOrdersService.query().subscribe((res: HttpResponse<ISuppliesOrders[]>) => (this.suppliesOrders = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSuppliesOrders();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISuppliesOrders): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSuppliesOrders(): void {
    this.eventSubscriber = this.eventManager.subscribe('suppliesOrdersListModification', () => this.loadAll());
  }

  delete(suppliesOrders: ISuppliesOrders): void {
    const modalRef = this.modalService.open(SuppliesOrdersDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.suppliesOrders = suppliesOrders;
  }
}
