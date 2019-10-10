import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Box } from 'app/shared/model/box.model';
import { BoxService } from './box.service';
import { BoxComponent } from './box.component';
import { BoxDetailComponent } from './box-detail.component';
import { BoxUpdateComponent } from './box-update.component';
import { BoxDeletePopupComponent } from './box-delete-dialog.component';
import { IBox } from 'app/shared/model/box.model';

@Injectable({ providedIn: 'root' })
export class BoxResolve implements Resolve<IBox> {
  constructor(private service: BoxService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBox> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Box>) => response.ok),
        map((box: HttpResponse<Box>) => box.body)
      );
    }
    return of(new Box());
  }
}

export const boxRoute: Routes = [
  {
    path: '',
    component: BoxComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.box.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BoxDetailComponent,
    resolve: {
      box: BoxResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.box.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BoxUpdateComponent,
    resolve: {
      box: BoxResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.box.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BoxUpdateComponent,
    resolve: {
      box: BoxResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.box.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const boxPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BoxDeletePopupComponent,
    resolve: {
      box: BoxResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.box.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
