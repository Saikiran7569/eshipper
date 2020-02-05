import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICustomsTaxBillTo, CustomsTaxBillTo } from 'app/shared/model/customs-tax-bill-to.model';
import { CustomsTaxBillToService } from './customs-tax-bill-to.service';

@Component({
  selector: 'jhi-customs-tax-bill-to-update',
  templateUrl: './customs-tax-bill-to-update.component.html'
})
export class CustomsTaxBillToUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    billTo: []
  });

  constructor(
    protected customsTaxBillToService: CustomsTaxBillToService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ customsTaxBillTo }) => {
      this.updateForm(customsTaxBillTo);
    });
  }

  updateForm(customsTaxBillTo: ICustomsTaxBillTo): void {
    this.editForm.patchValue({
      id: customsTaxBillTo.id,
      billTo: customsTaxBillTo.billTo
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const customsTaxBillTo = this.createFromForm();
    if (customsTaxBillTo.id !== undefined) {
      this.subscribeToSaveResponse(this.customsTaxBillToService.update(customsTaxBillTo));
    } else {
      this.subscribeToSaveResponse(this.customsTaxBillToService.create(customsTaxBillTo));
    }
  }

  private createFromForm(): ICustomsTaxBillTo {
    return {
      ...new CustomsTaxBillTo(),
      id: this.editForm.get(['id'])!.value,
      billTo: this.editForm.get(['billTo'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomsTaxBillTo>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
