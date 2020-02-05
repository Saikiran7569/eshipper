import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMonthlyShipments } from 'app/shared/model/monthly-shipments.model';
import { MonthlyShipmentsService } from './monthly-shipments.service';
import { MonthlyShipmentsDeleteDialogComponent } from './monthly-shipments-delete-dialog.component';

@Component({
  selector: 'jhi-monthly-shipments',
  templateUrl: './monthly-shipments.component.html'
})
export class MonthlyShipmentsComponent implements OnInit, OnDestroy {
  monthlyShipments?: IMonthlyShipments[];
  eventSubscriber?: Subscription;

  constructor(
    protected monthlyShipmentsService: MonthlyShipmentsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.monthlyShipmentsService.query().subscribe((res: HttpResponse<IMonthlyShipments[]>) => {
      this.monthlyShipments = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMonthlyShipments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMonthlyShipments): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMonthlyShipments(): void {
    this.eventSubscriber = this.eventManager.subscribe('monthlyShipmentsListModification', () => this.loadAll());
  }

  delete(monthlyShipments: IMonthlyShipments): void {
    const modalRef = this.modalService.open(MonthlyShipmentsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.monthlyShipments = monthlyShipments;
  }
}
