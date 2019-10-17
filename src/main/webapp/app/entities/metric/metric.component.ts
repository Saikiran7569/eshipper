import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager } from 'ng-jhipster';

import { IMetric } from 'app/shared/model/metric.model';
import { AccountService } from 'app/core/auth/account.service';
import { MetricService } from './metric.service';

@Component({
  selector: 'jhi-metric',
  templateUrl: './metric.component.html'
})
export class MetricComponent implements OnInit, OnDestroy {
  metrics: IMetric[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(protected metricService: MetricService, protected eventManager: JhiEventManager, protected accountService: AccountService) {}

  loadAll() {
    this.metricService
      .query()
      .pipe(
        filter((res: HttpResponse<IMetric[]>) => res.ok),
        map((res: HttpResponse<IMetric[]>) => res.body)
      )
      .subscribe((res: IMetric[]) => {
        this.metrics = res;
      });
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().subscribe(account => {
      this.currentAccount = account;
    });
    this.registerChangeInMetrics();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMetric) {
    return item.id;
  }

  registerChangeInMetrics() {
    this.eventSubscriber = this.eventManager.subscribe('metricListModification', response => this.loadAll());
  }
}
