import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUser11, User11 } from 'app/shared/model/user-11.model';
import { User11Service } from './user-11.service';
import { User11Component } from './user-11.component';
import { User11DetailComponent } from './user-11-detail.component';
import { User11UpdateComponent } from './user-11-update.component';

@Injectable({ providedIn: 'root' })
export class User11Resolve implements Resolve<IUser11> {
  constructor(private service: User11Service, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUser11> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((user11: HttpResponse<User11>) => {
          if (user11.body) {
            return of(user11.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new User11());
  }
}

export const user11Route: Routes = [
  {
    path: '',
    component: User11Component,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.user11.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: User11DetailComponent,
    resolve: {
      user11: User11Resolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.user11.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: User11UpdateComponent,
    resolve: {
      user11: User11Resolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.user11.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: User11UpdateComponent,
    resolve: {
      user11: User11Resolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.user11.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
