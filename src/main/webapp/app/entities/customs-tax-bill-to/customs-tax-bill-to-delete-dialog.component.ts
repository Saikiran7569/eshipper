import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustomsTaxBillTo } from 'app/shared/model/customs-tax-bill-to.model';
import { CustomsTaxBillToService } from './customs-tax-bill-to.service';

@Component({
  templateUrl: './customs-tax-bill-to-delete-dialog.component.html'
})
export class CustomsTaxBillToDeleteDialogComponent {
  customsTaxBillTo?: ICustomsTaxBillTo;

  constructor(
    protected customsTaxBillToService: CustomsTaxBillToService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.customsTaxBillToService.delete(id).subscribe(() => {
      this.eventManager.broadcast('customsTaxBillToListModification');
      this.activeModal.close();
    });
  }
}
