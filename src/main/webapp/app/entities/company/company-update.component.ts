import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICompany, Company } from 'app/shared/model/company.model';
import { CompanyService } from './company.service';

@Component({
  selector: 'jhi-company-update',
  templateUrl: './company-update.component.html'
})
export class CompanyUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    accountNumber: [null, [Validators.maxLength(255)]],
    name: [null, [Validators.maxLength(255)]],
    address1: [null, [Validators.maxLength(255)]],
    address2: [null, [Validators.maxLength(255)]],
    postalCode: [null, [Validators.maxLength(255)]],
    phone: [null, [Validators.maxLength(255)]],
    email: [null, [Validators.maxLength(255)]],
    timeZone: [null, [Validators.maxLength(255)]],
    costAccount: [null, [Validators.max(4)]],
    dateCreated: [],
    creator: [null, [Validators.max(20)]],
    contact: [null, [Validators.maxLength(255)]],
    isShopifyEnable: [],
    defaultSignatureOption: [null, [Validators.max(10)]]
  });

  constructor(protected companyService: CompanyService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ company }) => {
      this.updateForm(company);
    });
  }

  updateForm(company: ICompany) {
    this.editForm.patchValue({
      id: company.id,
      accountNumber: company.accountNumber,
      name: company.name,
      address1: company.address1,
      address2: company.address2,
      postalCode: company.postalCode,
      phone: company.phone,
      email: company.email,
      timeZone: company.timeZone,
      costAccount: company.costAccount,
      dateCreated: company.dateCreated != null ? company.dateCreated.format(DATE_TIME_FORMAT) : null,
      creator: company.creator,
      contact: company.contact,
      isShopifyEnable: company.isShopifyEnable,
      defaultSignatureOption: company.defaultSignatureOption
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const company = this.createFromForm();
    if (company.id !== undefined) {
      this.subscribeToSaveResponse(this.companyService.update(company));
    } else {
      this.subscribeToSaveResponse(this.companyService.create(company));
    }
  }

  private createFromForm(): ICompany {
    return {
      ...new Company(),
      id: this.editForm.get(['id']).value,
      accountNumber: this.editForm.get(['accountNumber']).value,
      name: this.editForm.get(['name']).value,
      address1: this.editForm.get(['address1']).value,
      address2: this.editForm.get(['address2']).value,
      postalCode: this.editForm.get(['postalCode']).value,
      phone: this.editForm.get(['phone']).value,
      email: this.editForm.get(['email']).value,
      timeZone: this.editForm.get(['timeZone']).value,
      costAccount: this.editForm.get(['costAccount']).value,
      dateCreated:
        this.editForm.get(['dateCreated']).value != null ? moment(this.editForm.get(['dateCreated']).value, DATE_TIME_FORMAT) : undefined,
      creator: this.editForm.get(['creator']).value,
      contact: this.editForm.get(['contact']).value,
      isShopifyEnable: this.editForm.get(['isShopifyEnable']).value,
      defaultSignatureOption: this.editForm.get(['defaultSignatureOption']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompany>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
