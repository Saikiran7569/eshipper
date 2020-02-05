import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMonthlyShipments, MonthlyShipments } from 'app/shared/model/monthly-shipments.model';
import { MonthlyShipmentsService } from './monthly-shipments.service';

@Component({
  selector: 'jhi-monthly-shipments-update',
  templateUrl: './monthly-shipments-update.component.html'
})
export class MonthlyShipmentsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    range: []
  });

  constructor(
    protected monthlyShipmentsService: MonthlyShipmentsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ monthlyShipments }) => {
      this.updateForm(monthlyShipments);
    });
  }

  updateForm(monthlyShipments: IMonthlyShipments): void {
    this.editForm.patchValue({
      id: monthlyShipments.id,
      range: monthlyShipments.range
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const monthlyShipments = this.createFromForm();
    if (monthlyShipments.id !== undefined) {
      this.subscribeToSaveResponse(this.monthlyShipmentsService.update(monthlyShipments));
    } else {
      this.subscribeToSaveResponse(this.monthlyShipmentsService.create(monthlyShipments));
    }
  }

  private createFromForm(): IMonthlyShipments {
    return {
      ...new MonthlyShipments(),
      id: this.editForm.get(['id'])!.value,
      range: this.editForm.get(['range'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMonthlyShipments>>): void {
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
