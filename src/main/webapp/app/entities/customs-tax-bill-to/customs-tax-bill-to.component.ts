import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICustomsTaxBillTo } from 'app/shared/model/customs-tax-bill-to.model';
import { CustomsTaxBillToService } from './customs-tax-bill-to.service';
import { CustomsTaxBillToDeleteDialogComponent } from './customs-tax-bill-to-delete-dialog.component';

@Component({
  selector: 'jhi-customs-tax-bill-to',
  templateUrl: './customs-tax-bill-to.component.html'
})
export class CustomsTaxBillToComponent implements OnInit, OnDestroy {
  customsTaxBillTos?: ICustomsTaxBillTo[];
  eventSubscriber?: Subscription;

  constructor(
    protected customsTaxBillToService: CustomsTaxBillToService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.customsTaxBillToService.query().subscribe((res: HttpResponse<ICustomsTaxBillTo[]>) => {
      this.customsTaxBillTos = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCustomsTaxBillTos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICustomsTaxBillTo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCustomsTaxBillTos(): void {
    this.eventSubscriber = this.eventManager.subscribe('customsTaxBillToListModification', () => this.loadAll());
  }

  delete(customsTaxBillTo: ICustomsTaxBillTo): void {
    const modalRef = this.modalService.open(CustomsTaxBillToDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.customsTaxBillTo = customsTaxBillTo;
  }
}
