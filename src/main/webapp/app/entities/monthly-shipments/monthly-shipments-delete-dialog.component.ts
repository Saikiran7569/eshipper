import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMonthlyShipments } from 'app/shared/model/monthly-shipments.model';
import { MonthlyShipmentsService } from './monthly-shipments.service';

@Component({
  templateUrl: './monthly-shipments-delete-dialog.component.html'
})
export class MonthlyShipmentsDeleteDialogComponent {
  monthlyShipments?: IMonthlyShipments;

  constructor(
    protected monthlyShipmentsService: MonthlyShipmentsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.monthlyShipmentsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('monthlyShipmentsListModification');
      this.activeModal.close();
    });
  }
}
