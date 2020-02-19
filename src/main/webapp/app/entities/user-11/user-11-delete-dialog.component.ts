import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUser11 } from 'app/shared/model/user-11.model';
import { User11Service } from './user-11.service';

@Component({
  templateUrl: './user-11-delete-dialog.component.html'
})
export class User11DeleteDialogComponent {
  user11?: IUser11;

  constructor(protected user11Service: User11Service, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.user11Service.delete(id).subscribe(() => {
      this.eventManager.broadcast('user11ListModification');
      this.activeModal.close();
    });
  }
}
