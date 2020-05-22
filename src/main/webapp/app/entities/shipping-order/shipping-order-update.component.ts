import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IShippingOrder, ShippingOrder } from 'app/shared/model/shipping-order.model';
import { ShippingOrderService } from './shipping-order.service';
import { ICommodityType } from 'app/shared/model/commodity-type.model';
import { CommodityTypeService } from 'app/entities/commodity-type/commodity-type.service';

@Component({
  selector: 'jhi-shipping-order-update',
  templateUrl: './shipping-order-update.component.html',
})
export class ShippingOrderUpdateComponent implements OnInit {
  isSaving = false;
  commoditytypes: ICommodityType[] = [];

  editForm = this.fb.group({
    id: [],
    commodityTypeId: [],
  });

  constructor(
    protected shippingOrderService: ShippingOrderService,
    protected commodityTypeService: CommodityTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shippingOrder }) => {
      this.updateForm(shippingOrder);

      this.commodityTypeService.query().subscribe((res: HttpResponse<ICommodityType[]>) => (this.commoditytypes = res.body || []));
    });
  }

  updateForm(shippingOrder: IShippingOrder): void {
    this.editForm.patchValue({
      id: shippingOrder.id,
      commodityTypeId: shippingOrder.commodityTypeId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const shippingOrder = this.createFromForm();
    if (shippingOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.shippingOrderService.update(shippingOrder));
    } else {
      this.subscribeToSaveResponse(this.shippingOrderService.create(shippingOrder));
    }
  }

  private createFromForm(): IShippingOrder {
    return {
      ...new ShippingOrder(),
      id: this.editForm.get(['id'])!.value,
      commodityTypeId: this.editForm.get(['commodityTypeId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShippingOrder>>): void {
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

  trackById(index: number, item: ICommodityType): any {
    return item.id;
  }
}
