import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMonthlyShipments, MonthlyShipments } from 'app/shared/model/monthly-shipments.model';
import { MonthlyShipmentsService } from './monthly-shipments.service';
import { MonthlyShipmentsComponent } from './monthly-shipments.component';
import { MonthlyShipmentsDetailComponent } from './monthly-shipments-detail.component';
import { MonthlyShipmentsUpdateComponent } from './monthly-shipments-update.component';

@Injectable({ providedIn: 'root' })
export class MonthlyShipmentsResolve implements Resolve<IMonthlyShipments> {
  constructor(private service: MonthlyShipmentsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMonthlyShipments> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((monthlyShipments: HttpResponse<MonthlyShipments>) => {
          if (monthlyShipments.body) {
            return of(monthlyShipments.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MonthlyShipments());
  }
}

export const monthlyShipmentsRoute: Routes = [
  {
    path: '',
    component: MonthlyShipmentsComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.monthlyShipments.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MonthlyShipmentsDetailComponent,
    resolve: {
      monthlyShipments: MonthlyShipmentsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.monthlyShipments.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MonthlyShipmentsUpdateComponent,
    resolve: {
      monthlyShipments: MonthlyShipmentsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.monthlyShipments.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MonthlyShipmentsUpdateComponent,
    resolve: {
      monthlyShipments: MonthlyShipmentsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.monthlyShipments.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
