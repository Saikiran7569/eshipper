import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBox } from 'app/shared/model/box.model';
import { BoxService } from './box.service';

@Component({
  templateUrl: './box-delete-dialog.component.html'
})
export class BoxDeleteDialogComponent {
  box: IBox;

  constructor(protected boxService: BoxService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.boxService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'boxListModification',
        content: 'Deleted an box'
      });
      this.activeModal.dismiss(true);
    });
  }
}
