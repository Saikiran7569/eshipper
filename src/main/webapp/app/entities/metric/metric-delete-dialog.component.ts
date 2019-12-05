import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMetric } from 'app/shared/model/metric.model';
import { MetricService } from './metric.service';

@Component({
  templateUrl: './metric-delete-dialog.component.html'
})
export class MetricDeleteDialogComponent {
  metric: IMetric;

  constructor(protected metricService: MetricService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.metricService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'metricListModification',
        content: 'Deleted an metric'
      });
      this.activeModal.dismiss(true);
    });
  }
}
