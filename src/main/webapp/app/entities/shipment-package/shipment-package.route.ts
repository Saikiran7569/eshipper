import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ShipmentPackage } from 'app/shared/model/shipment-package.model';
import { ShipmentPackageService } from './shipment-package.service';
import { ShipmentPackageComponent } from './shipment-package.component';
import { ShipmentPackageDetailComponent } from './shipment-package-detail.component';
import { ShipmentPackageUpdateComponent } from './shipment-package-update.component';
import { ShipmentPackageDeletePopupComponent } from './shipment-package-delete-dialog.component';
import { IShipmentPackage } from 'app/shared/model/shipment-package.model';

@Injectable({ providedIn: 'root' })
export class ShipmentPackageResolve implements Resolve<IShipmentPackage> {
  constructor(private service: ShipmentPackageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IShipmentPackage> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ShipmentPackage>) => response.ok),
        map((shipmentPackage: HttpResponse<ShipmentPackage>) => shipmentPackage.body)
      );
    }
    return of(new ShipmentPackage());
  }
}

export const shipmentPackageRoute: Routes = [
  {
    path: '',
    component: ShipmentPackageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.shipmentPackage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ShipmentPackageDetailComponent,
    resolve: {
      shipmentPackage: ShipmentPackageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shipmentPackage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ShipmentPackageUpdateComponent,
    resolve: {
      shipmentPackage: ShipmentPackageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shipmentPackage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ShipmentPackageUpdateComponent,
    resolve: {
      shipmentPackage: ShipmentPackageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shipmentPackage.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const shipmentPackagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ShipmentPackageDeletePopupComponent,
    resolve: {
      shipmentPackage: ShipmentPackageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shipmentPackage.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
