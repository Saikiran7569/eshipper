import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Metric } from 'app/shared/model/metric.model';
import { MetricService } from './metric.service';
import { MetricComponent } from './metric.component';
import { MetricDetailComponent } from './metric-detail.component';
import { MetricUpdateComponent } from './metric-update.component';
import { IMetric } from 'app/shared/model/metric.model';

@Injectable({ providedIn: 'root' })
export class MetricResolve implements Resolve<IMetric> {
  constructor(private service: MetricService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMetric> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((metric: HttpResponse<Metric>) => metric.body));
    }
    return of(new Metric());
  }
}

export const metricRoute: Routes = [
  {
    path: '',
    component: MetricComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.metric.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MetricDetailComponent,
    resolve: {
      metric: MetricResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.metric.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MetricUpdateComponent,
    resolve: {
      metric: MetricResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.metric.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MetricUpdateComponent,
    resolve: {
      metric: MetricResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.metric.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
