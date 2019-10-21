import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager } from 'ng-jhipster';

import { ICity } from 'app/shared/model/city.model';
import { AccountService } from 'app/core/auth/account.service';
import { CityService } from './city.service';

@Component({
  selector: 'jhi-city',
  templateUrl: './city.component.html'
})
export class CityComponent implements OnInit, OnDestroy {
  cities: ICity[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(protected cityService: CityService, protected eventManager: JhiEventManager, protected accountService: AccountService) {}

  loadAll() {
    this.cityService
      .query()
      .pipe(
        filter((res: HttpResponse<ICity[]>) => res.ok),
        map((res: HttpResponse<ICity[]>) => res.body)
      )
      .subscribe((res: ICity[]) => {
        this.cities = res;
      });
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().subscribe(account => {
      this.currentAccount = account;
    });
    this.registerChangeInCities();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICity) {
    return item.id;
  }

  registerChangeInCities() {
    this.eventSubscriber = this.eventManager.subscribe('cityListModification', response => this.loadAll());
  }
}
