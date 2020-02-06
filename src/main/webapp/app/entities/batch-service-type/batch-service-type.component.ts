import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBatchServiceType } from 'app/shared/model/batch-service-type.model';
import { BatchServiceTypeService } from './batch-service-type.service';
import { BatchServiceTypeDeleteDialogComponent } from './batch-service-type-delete-dialog.component';

@Component({
  selector: 'jhi-batch-service-type',
  templateUrl: './batch-service-type.component.html'
})
export class BatchServiceTypeComponent implements OnInit, OnDestroy {
  batchServiceTypes?: IBatchServiceType[];
  eventSubscriber?: Subscription;

  constructor(
    protected batchServiceTypeService: BatchServiceTypeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.batchServiceTypeService.query().subscribe((res: HttpResponse<IBatchServiceType[]>) => {
      this.batchServiceTypes = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBatchServiceTypes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBatchServiceType): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBatchServiceTypes(): void {
    this.eventSubscriber = this.eventManager.subscribe('batchServiceTypeListModification', () => this.loadAll());
  }

  delete(batchServiceType: IBatchServiceType): void {
    const modalRef = this.modalService.open(BatchServiceTypeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.batchServiceType = batchServiceType;
  }
}
