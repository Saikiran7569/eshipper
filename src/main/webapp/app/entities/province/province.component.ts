import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager } from 'ng-jhipster';

import { IProvince } from 'app/shared/model/province.model';
import { AccountService } from 'app/core/auth/account.service';
import { ProvinceService } from './province.service';

@Component({
  selector: 'jhi-province',
  templateUrl: './province.component.html'
})
export class ProvinceComponent implements OnInit, OnDestroy {
  provinces: IProvince[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected provinceService: ProvinceService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.provinceService
      .query()
      .pipe(
        filter((res: HttpResponse<IProvince[]>) => res.ok),
        map((res: HttpResponse<IProvince[]>) => res.body)
      )
      .subscribe((res: IProvince[]) => {
        this.provinces = res;
      });
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().subscribe(account => {
      this.currentAccount = account;
    });
    this.registerChangeInProvinces();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProvince) {
    return item.id;
  }

  registerChangeInProvinces() {
    this.eventSubscriber = this.eventManager.subscribe('provinceListModification', response => this.loadAll());
  }
}
