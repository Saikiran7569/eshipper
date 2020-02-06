import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBatchServiceType } from 'app/shared/model/batch-service-type.model';
import { BatchServiceTypeService } from './batch-service-type.service';

@Component({
  templateUrl: './batch-service-type-delete-dialog.component.html'
})
export class BatchServiceTypeDeleteDialogComponent {
  batchServiceType?: IBatchServiceType;

  constructor(
    protected batchServiceTypeService: BatchServiceTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.batchServiceTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('batchServiceTypeListModification');
      this.activeModal.close();
    });
  }
}
