import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICustomer, Customer } from 'app/shared/model/customer.model';
import { CustomerService } from './customer.service';

@Component({
  selector: 'jhi-customer-update',
  templateUrl: './customer-update.component.html'
})
export class CustomerUpdateComponent implements OnInit {
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

  constructor(protected customerService: CustomerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ customer }) => {
      this.updateForm(customer);
    });
  }

  updateForm(customer: ICustomer) {
    this.editForm.patchValue({
      id: customer.id,
      accountNumber: customer.accountNumber,
      name: customer.name,
      address1: customer.address1,
      address2: customer.address2,
      postalCode: customer.postalCode,
      phone: customer.phone,
      email: customer.email,
      timeZone: customer.timeZone,
      costAccount: customer.costAccount,
      dateCreated: customer.dateCreated != null ? customer.dateCreated.format(DATE_TIME_FORMAT) : null,
      creator: customer.creator,
      contact: customer.contact,
      isShopifyEnable: customer.isShopifyEnable,
      defaultSignatureOption: customer.defaultSignatureOption
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const customer = this.createFromForm();
    if (customer.id !== undefined) {
      this.subscribeToSaveResponse(this.customerService.update(customer));
    } else {
      this.subscribeToSaveResponse(this.customerService.create(customer));
    }
  }

  private createFromForm(): ICustomer {
    return {
      ...new Customer(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomer>>) {
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
