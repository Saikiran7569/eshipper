import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIndustry } from 'app/shared/model/industry.model';
import { IndustryService } from './industry.service';

@Component({
  templateUrl: './industry-delete-dialog.component.html'
})
export class IndustryDeleteDialogComponent {
  industry?: IIndustry;

  constructor(protected industryService: IndustryService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.industryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('industryListModification');
      this.activeModal.close();
    });
  }
}
