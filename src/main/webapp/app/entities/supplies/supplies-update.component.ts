import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ISupplies, Supplies } from 'app/shared/model/supplies.model';
import { SuppliesService } from './supplies.service';
import { ICarrier } from 'app/shared/model/carrier.model';
import { CarrierService } from 'app/entities/carrier/carrier.service';

@Component({
  selector: 'jhi-supplies-update',
  templateUrl: './supplies-update.component.html'
})
export class SuppliesUpdateComponent implements OnInit {
  isSaving = false;

  carriers: ICarrier[] = [];

  editForm = this.fb.group({
    id: [],
    item: [],
    name: [],
    suppliesId: []
  });

  constructor(
    protected suppliesService: SuppliesService,
    protected carrierService: CarrierService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ supplies }) => {
      this.updateForm(supplies);

      this.carrierService
        .query()
        .pipe(
          map((res: HttpResponse<ICarrier[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICarrier[]) => (this.carriers = resBody));
    });
  }

  updateForm(supplies: ISupplies): void {
    this.editForm.patchValue({
      id: supplies.id,
      item: supplies.item,
      name: supplies.name,
      suppliesId: supplies.suppliesId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const supplies = this.createFromForm();
    if (supplies.id !== undefined) {
      this.subscribeToSaveResponse(this.suppliesService.update(supplies));
    } else {
      this.subscribeToSaveResponse(this.suppliesService.create(supplies));
    }
  }

  private createFromForm(): ISupplies {
    return {
      ...new Supplies(),
      id: this.editForm.get(['id'])!.value,
      item: this.editForm.get(['item'])!.value,
      name: this.editForm.get(['name'])!.value,
      suppliesId: this.editForm.get(['suppliesId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISupplies>>): void {
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
