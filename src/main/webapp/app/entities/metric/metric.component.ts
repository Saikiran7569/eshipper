import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMetric } from 'app/shared/model/metric.model';
import { MetricService } from './metric.service';
import { MetricDeleteDialogComponent } from './metric-delete-dialog.component';

@Component({
  selector: 'jhi-metric',
  templateUrl: './metric.component.html'
})
export class MetricComponent implements OnInit, OnDestroy {
  metrics: IMetric[];
  eventSubscriber: Subscription;

  constructor(protected metricService: MetricService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll() {
    this.metricService.query().subscribe((res: HttpResponse<IMetric[]>) => {
      this.metrics = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInMetrics();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMetric) {
    return item.id;
  }

  registerChangeInMetrics() {
    this.eventSubscriber = this.eventManager.subscribe('metricListModification', () => this.loadAll());
  }

  delete(metric: IMetric) {
    const modalRef = this.modalService.open(MetricDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.metric = metric;
  }
}
