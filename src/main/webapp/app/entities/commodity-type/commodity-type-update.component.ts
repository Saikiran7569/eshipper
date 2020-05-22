import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICommodityType, CommodityType } from 'app/shared/model/commodity-type.model';
import { CommodityTypeService } from './commodity-type.service';

@Component({
  selector: 'jhi-commodity-type-update',
  templateUrl: './commodity-type-update.component.html',
})
export class CommodityTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
  });

  constructor(protected commodityTypeService: CommodityTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commodityType }) => {
      this.updateForm(commodityType);
    });
  }

  updateForm(commodityType: ICommodityType): void {
    this.editForm.patchValue({
      id: commodityType.id,
      name: commodityType.name,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commodityType = this.createFromForm();
    if (commodityType.id !== undefined) {
      this.subscribeToSaveResponse(this.commodityTypeService.update(commodityType));
    } else {
      this.subscribeToSaveResponse(this.commodityTypeService.create(commodityType));
    }
  }

  private createFromForm(): ICommodityType {
    return {
      ...new CommodityType(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommodityType>>): void {
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
