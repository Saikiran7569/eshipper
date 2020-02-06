import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBatchServiceType, BatchServiceType } from 'app/shared/model/batch-service-type.model';
import { BatchServiceTypeService } from './batch-service-type.service';

@Component({
  selector: 'jhi-batch-service-type-update',
  templateUrl: './batch-service-type-update.component.html'
})
export class BatchServiceTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    serviceName: [],
    value: []
  });

  constructor(
    protected batchServiceTypeService: BatchServiceTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ batchServiceType }) => {
      this.updateForm(batchServiceType);
    });
  }

  updateForm(batchServiceType: IBatchServiceType): void {
    this.editForm.patchValue({
      id: batchServiceType.id,
      serviceName: batchServiceType.serviceName,
      value: batchServiceType.value
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const batchServiceType = this.createFromForm();
    if (batchServiceType.id !== undefined) {
      this.subscribeToSaveResponse(this.batchServiceTypeService.update(batchServiceType));
    } else {
      this.subscribeToSaveResponse(this.batchServiceTypeService.create(batchServiceType));
    }
  }

  private createFromForm(): IBatchServiceType {
    return {
      ...new BatchServiceType(),
      id: this.editForm.get(['id'])!.value,
      serviceName: this.editForm.get(['serviceName'])!.value,
      value: this.editForm.get(['value'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBatchServiceType>>): void {
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
