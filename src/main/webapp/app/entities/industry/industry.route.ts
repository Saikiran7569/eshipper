import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IIndustry, Industry } from 'app/shared/model/industry.model';
import { IndustryService } from './industry.service';
import { IndustryComponent } from './industry.component';
import { IndustryDetailComponent } from './industry-detail.component';
import { IndustryUpdateComponent } from './industry-update.component';

@Injectable({ providedIn: 'root' })
export class IndustryResolve implements Resolve<IIndustry> {
  constructor(private service: IndustryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IIndustry> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((industry: HttpResponse<Industry>) => {
          if (industry.body) {
            return of(industry.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Industry());
  }
}

export const industryRoute: Routes = [
  {
    path: '',
    component: IndustryComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.industry.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: IndustryDetailComponent,
    resolve: {
      industry: IndustryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.industry.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: IndustryUpdateComponent,
    resolve: {
      industry: IndustryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.industry.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: IndustryUpdateComponent,
    resolve: {
      industry: IndustryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.industry.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
