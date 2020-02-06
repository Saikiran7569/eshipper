import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBatchServiceType, BatchServiceType } from 'app/shared/model/batch-service-type.model';
import { BatchServiceTypeService } from './batch-service-type.service';
import { BatchServiceTypeComponent } from './batch-service-type.component';
import { BatchServiceTypeDetailComponent } from './batch-service-type-detail.component';
import { BatchServiceTypeUpdateComponent } from './batch-service-type-update.component';

@Injectable({ providedIn: 'root' })
export class BatchServiceTypeResolve implements Resolve<IBatchServiceType> {
  constructor(private service: BatchServiceTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBatchServiceType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((batchServiceType: HttpResponse<BatchServiceType>) => {
          if (batchServiceType.body) {
            return of(batchServiceType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BatchServiceType());
  }
}

export const batchServiceTypeRoute: Routes = [
  {
    path: '',
    component: BatchServiceTypeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.batchServiceType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BatchServiceTypeDetailComponent,
    resolve: {
      batchServiceType: BatchServiceTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.batchServiceType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BatchServiceTypeUpdateComponent,
    resolve: {
      batchServiceType: BatchServiceTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.batchServiceType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BatchServiceTypeUpdateComponent,
    resolve: {
      batchServiceType: BatchServiceTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.batchServiceType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
