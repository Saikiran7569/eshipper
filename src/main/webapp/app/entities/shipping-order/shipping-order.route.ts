import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IShippingOrder, ShippingOrder } from 'app/shared/model/shipping-order.model';
import { ShippingOrderService } from './shipping-order.service';
import { ShippingOrderComponent } from './shipping-order.component';
import { ShippingOrderDetailComponent } from './shipping-order-detail.component';
import { ShippingOrderUpdateComponent } from './shipping-order-update.component';

@Injectable({ providedIn: 'root' })
export class ShippingOrderResolve implements Resolve<IShippingOrder> {
  constructor(private service: ShippingOrderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IShippingOrder> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((shippingOrder: HttpResponse<ShippingOrder>) => {
          if (shippingOrder.body) {
            return of(shippingOrder.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ShippingOrder());
  }
}

export const shippingOrderRoute: Routes = [
  {
    path: '',
    component: ShippingOrderComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.shippingOrder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ShippingOrderDetailComponent,
    resolve: {
      shippingOrder: ShippingOrderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.shippingOrder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ShippingOrderUpdateComponent,
    resolve: {
      shippingOrder: ShippingOrderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.shippingOrder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ShippingOrderUpdateComponent,
    resolve: {
      shippingOrder: ShippingOrderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.shippingOrder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
