import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISupplies, Supplies } from 'app/shared/model/supplies.model';
import { SuppliesService } from './supplies.service';
import { SuppliesComponent } from './supplies.component';
import { SuppliesDetailComponent } from './supplies-detail.component';
import { SuppliesUpdateComponent } from './supplies-update.component';

@Injectable({ providedIn: 'root' })
export class SuppliesResolve implements Resolve<ISupplies> {
  constructor(private service: SuppliesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISupplies> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((supplies: HttpResponse<Supplies>) => {
          if (supplies.body) {
            return of(supplies.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Supplies());
  }
}

export const suppliesRoute: Routes = [
  {
    path: '',
    component: SuppliesComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.supplies.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SuppliesDetailComponent,
    resolve: {
      supplies: SuppliesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.supplies.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SuppliesUpdateComponent,
    resolve: {
      supplies: SuppliesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.supplies.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SuppliesUpdateComponent,
    resolve: {
      supplies: SuppliesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.supplies.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
