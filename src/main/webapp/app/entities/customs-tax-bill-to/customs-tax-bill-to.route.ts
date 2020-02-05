import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICustomsTaxBillTo, CustomsTaxBillTo } from 'app/shared/model/customs-tax-bill-to.model';
import { CustomsTaxBillToService } from './customs-tax-bill-to.service';
import { CustomsTaxBillToComponent } from './customs-tax-bill-to.component';
import { CustomsTaxBillToDetailComponent } from './customs-tax-bill-to-detail.component';
import { CustomsTaxBillToUpdateComponent } from './customs-tax-bill-to-update.component';

@Injectable({ providedIn: 'root' })
export class CustomsTaxBillToResolve implements Resolve<ICustomsTaxBillTo> {
  constructor(private service: CustomsTaxBillToService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICustomsTaxBillTo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((customsTaxBillTo: HttpResponse<CustomsTaxBillTo>) => {
          if (customsTaxBillTo.body) {
            return of(customsTaxBillTo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CustomsTaxBillTo());
  }
}

export const customsTaxBillToRoute: Routes = [
  {
    path: '',
    component: CustomsTaxBillToComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.customsTaxBillTo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustomsTaxBillToDetailComponent,
    resolve: {
      customsTaxBillTo: CustomsTaxBillToResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.customsTaxBillTo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustomsTaxBillToUpdateComponent,
    resolve: {
      customsTaxBillTo: CustomsTaxBillToResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.customsTaxBillTo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustomsTaxBillToUpdateComponent,
    resolve: {
      customsTaxBillTo: CustomsTaxBillToResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.customsTaxBillTo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
