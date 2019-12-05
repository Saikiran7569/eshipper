import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BoxPackageType } from 'app/shared/model/box-package-type.model';
import { BoxPackageTypeService } from './box-package-type.service';
import { BoxPackageTypeComponent } from './box-package-type.component';
import { BoxPackageTypeDetailComponent } from './box-package-type-detail.component';
import { BoxPackageTypeUpdateComponent } from './box-package-type-update.component';
import { IBoxPackageType } from 'app/shared/model/box-package-type.model';

@Injectable({ providedIn: 'root' })
export class BoxPackageTypeResolve implements Resolve<IBoxPackageType> {
  constructor(private service: BoxPackageTypeService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBoxPackageType> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((boxPackageType: HttpResponse<BoxPackageType>) => boxPackageType.body));
    }
    return of(new BoxPackageType());
  }
}

export const boxPackageTypeRoute: Routes = [
  {
    path: '',
    component: BoxPackageTypeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.boxPackageType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BoxPackageTypeDetailComponent,
    resolve: {
      boxPackageType: BoxPackageTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.boxPackageType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BoxPackageTypeUpdateComponent,
    resolve: {
      boxPackageType: BoxPackageTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.boxPackageType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BoxPackageTypeUpdateComponent,
    resolve: {
      boxPackageType: BoxPackageTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.boxPackageType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
