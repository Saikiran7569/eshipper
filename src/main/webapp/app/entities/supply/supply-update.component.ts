import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISupply, Supply } from 'app/shared/model/supply.model';
import { SupplyService } from './supply.service';
import { ICarrier } from 'app/shared/model/carrier.model';
import { CarrierService } from 'app/entities/carrier/carrier.service';

@Component({
  selector: 'jhi-supply-update',
  templateUrl: './supply-update.component.html'
})
export class SupplyUpdateComponent implements OnInit {
  isSaving = false;
  carriers: ICarrier[] = [];

  editForm = this.fb.group({
    id: [],
    itemName: [],
    logoPath: [],
    carrierId: []
  });

  constructor(
    protected supplyService: SupplyService,
    protected carrierService: CarrierService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ supply }) => {
      this.updateForm(supply);

      this.carrierService.query().subscribe((res: HttpResponse<ICarrier[]>) => (this.carriers = res.body || []));
    });
  }

  updateForm(supply: ISupply): void {
    this.editForm.patchValue({
      id: supply.id,
      itemName: supply.itemName,
      logoPath: supply.logoPath,
      carrierId: supply.carrierId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const supply = this.createFromForm();
    if (supply.id !== undefined) {
      this.subscribeToSaveResponse(this.supplyService.update(supply));
    } else {
      this.subscribeToSaveResponse(this.supplyService.create(supply));
    }
  }

  private createFromForm(): ISupply {
    return {
      ...new Supply(),
      id: this.editForm.get(['id'])!.value,
      itemName: this.editForm.get(['itemName'])!.value,
      logoPath: this.editForm.get(['logoPath'])!.value,
      carrierId: this.editForm.get(['carrierId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISupply>>): void {
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

  trackById(index: number, item: ICarrier): any {
    return item.id;
  }
}
