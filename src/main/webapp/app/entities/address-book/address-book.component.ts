import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAddressBook } from 'app/shared/model/address-book.model';
import { AccountService } from 'app/core/auth/account.service';
import { AddressBookService } from './address-book.service';

@Component({
  selector: 'jhi-address-book',
  templateUrl: './address-book.component.html'
})
export class AddressBookComponent implements OnInit, OnDestroy {
  addressBooks: IAddressBook[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected addressBookService: AddressBookService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.addressBookService
      .query()
      .pipe(
        filter((res: HttpResponse<IAddressBook[]>) => res.ok),
        map((res: HttpResponse<IAddressBook[]>) => res.body)
      )
      .subscribe(
        (res: IAddressBook[]) => {
          this.addressBooks = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInAddressBooks();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IAddressBook) {
    return item.id;
  }

  registerChangeInAddressBooks() {
    this.eventSubscriber = this.eventManager.subscribe('addressBookListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
