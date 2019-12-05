import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICompany } from 'app/shared/model/company.model';
import { CompanyService } from './company.service';
import { CompanyDeleteDialogComponent } from './company-delete-dialog.component';

@Component({
  selector: 'jhi-company',
  templateUrl: './company.component.html'
})
export class CompanyComponent implements OnInit, OnDestroy {
  companies: ICompany[];
  eventSubscriber: Subscription;

  constructor(protected companyService: CompanyService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll() {
    this.companyService.query().subscribe((res: HttpResponse<ICompany[]>) => {
      this.companies = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInCompanies();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICompany) {
    return item.id;
  }

  registerChangeInCompanies() {
    this.eventSubscriber = this.eventManager.subscribe('companyListModification', () => this.loadAll());
  }

  delete(company: ICompany) {
    const modalRef = this.modalService.open(CompanyDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.company = company;
  }
}
