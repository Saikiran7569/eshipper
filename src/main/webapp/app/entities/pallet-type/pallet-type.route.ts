import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PalletType } from 'app/shared/model/pallet-type.model';
import { PalletTypeService } from './pallet-type.service';
import { PalletTypeComponent } from './pallet-type.component';
import { PalletTypeDetailComponent } from './pallet-type-detail.component';
import { PalletTypeUpdateComponent } from './pallet-type-update.component';
import { PalletTypeDeletePopupComponent } from './pallet-type-delete-dialog.component';
import { IPalletType } from 'app/shared/model/pallet-type.model';

@Injectable({ providedIn: 'root' })
export class PalletTypeResolve implements Resolve<IPalletType> {
  constructor(private service: PalletTypeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPalletType> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PalletType>) => response.ok),
        map((palletType: HttpResponse<PalletType>) => palletType.body)
      );
    }
    return of(new PalletType());
  }
}

export const palletTypeRoute: Routes = [
  {
    path: '',
    component: PalletTypeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.palletType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PalletTypeDetailComponent,
    resolve: {
      palletType: PalletTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.palletType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PalletTypeUpdateComponent,
    resolve: {
      palletType: PalletTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.palletType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PalletTypeUpdateComponent,
    resolve: {
      palletType: PalletTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.palletType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const palletTypePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PalletTypeDeletePopupComponent,
    resolve: {
      palletType: PalletTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.palletType.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
