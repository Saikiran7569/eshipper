import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager } from 'ng-jhipster';

import { IPalletType } from 'app/shared/model/pallet-type.model';
import { AccountService } from 'app/core/auth/account.service';
import { PalletTypeService } from './pallet-type.service';

@Component({
  selector: 'jhi-pallet-type',
  templateUrl: './pallet-type.component.html'
})
export class PalletTypeComponent implements OnInit, OnDestroy {
  palletTypes: IPalletType[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected palletTypeService: PalletTypeService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.palletTypeService
      .query()
      .pipe(
        filter((res: HttpResponse<IPalletType[]>) => res.ok),
        map((res: HttpResponse<IPalletType[]>) => res.body)
      )
      .subscribe((res: IPalletType[]) => {
        this.palletTypes = res;
      });
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().subscribe(account => {
      this.currentAccount = account;
    });
    this.registerChangeInPalletTypes();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPalletType) {
    return item.id;
  }

  registerChangeInPalletTypes() {
    this.eventSubscriber = this.eventManager.subscribe('palletTypeListModification', response => this.loadAll());
  }
}
