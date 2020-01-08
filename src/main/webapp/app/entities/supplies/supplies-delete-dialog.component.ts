import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISupplies } from 'app/shared/model/supplies.model';
import { SuppliesService } from './supplies.service';

@Component({
  templateUrl: './supplies-delete-dialog.component.html'
})
export class SuppliesDeleteDialogComponent {
  supplies?: ISupplies;

  constructor(protected suppliesService: SuppliesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.suppliesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('suppliesListModification');
      this.activeModal.close();
    });
  }
}
