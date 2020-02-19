import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUser11 } from 'app/shared/model/user-11.model';
import { User11Service } from './user-11.service';
import { User11DeleteDialogComponent } from './user-11-delete-dialog.component';

@Component({
  selector: 'jhi-user-11',
  templateUrl: './user-11.component.html'
})
export class User11Component implements OnInit, OnDestroy {
  user11S?: IUser11[];
  eventSubscriber?: Subscription;

  constructor(protected user11Service: User11Service, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.user11Service.query().subscribe((res: HttpResponse<IUser11[]>) => (this.user11S = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUser11S();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUser11): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUser11S(): void {
    this.eventSubscriber = this.eventManager.subscribe('user11ListModification', () => this.loadAll());
  }

  delete(user11: IUser11): void {
    const modalRef = this.modalService.open(User11DeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.user11 = user11;
  }
}
