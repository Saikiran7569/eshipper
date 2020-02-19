import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISuppliesOrders, SuppliesOrders } from 'app/shared/model/supplies-orders.model';
import { SuppliesOrdersService } from './supplies-orders.service';
import { IUser11 } from 'app/shared/model/user-11.model';
import { User11Service } from 'app/entities/user-11/user-11.service';

@Component({
  selector: 'jhi-supplies-orders-update',
  templateUrl: './supplies-orders-update.component.html'
})
export class SuppliesOrdersUpdateComponent implements OnInit {
  isSaving = false;
  user11s: IUser11[] = [];
  createdDateDp: any;

  editForm = this.fb.group({
    id: [],
    itemName: [],
    quantity: [],
    createdDate: [],
    createdByUserId: []
  });

  constructor(
    protected suppliesOrdersService: SuppliesOrdersService,
    protected user11Service: User11Service,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ suppliesOrders }) => {
      this.updateForm(suppliesOrders);

      this.user11Service.query().subscribe((res: HttpResponse<IUser11[]>) => (this.user11s = res.body || []));
    });
  }

  updateForm(suppliesOrders: ISuppliesOrders): void {
    this.editForm.patchValue({
      id: suppliesOrders.id,
      itemName: suppliesOrders.itemName,
      quantity: suppliesOrders.quantity,
      createdDate: suppliesOrders.createdDate,
      createdByUserId: suppliesOrders.createdByUserId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const suppliesOrders = this.createFromForm();
    if (suppliesOrders.id !== undefined) {
      this.subscribeToSaveResponse(this.suppliesOrdersService.update(suppliesOrders));
    } else {
      this.subscribeToSaveResponse(this.suppliesOrdersService.create(suppliesOrders));
    }
  }

  private createFromForm(): ISuppliesOrders {
    return {
      ...new SuppliesOrders(),
      id: this.editForm.get(['id'])!.value,
      itemName: this.editForm.get(['itemName'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value,
      createdByUserId: this.editForm.get(['createdByUserId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISuppliesOrders>>): void {
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

  trackById(index: number, item: IUser11): any {
    return item.id;
  }
}
