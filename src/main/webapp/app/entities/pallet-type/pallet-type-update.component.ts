import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IPalletType, PalletType } from 'app/shared/model/pallet-type.model';
import { PalletTypeService } from './pallet-type.service';

@Component({
  selector: 'jhi-pallet-type-update',
  templateUrl: './pallet-type-update.component.html'
})
export class PalletTypeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: []
  });

  constructor(protected palletTypeService: PalletTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ palletType }) => {
      this.updateForm(palletType);
    });
  }

  updateForm(palletType: IPalletType) {
    this.editForm.patchValue({
      id: palletType.id
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const palletType = this.createFromForm();
    if (palletType.id !== undefined) {
      this.subscribeToSaveResponse(this.palletTypeService.update(palletType));
    } else {
      this.subscribeToSaveResponse(this.palletTypeService.create(palletType));
    }
  }

  private createFromForm(): IPalletType {
    return {
      ...new PalletType(),
      id: this.editForm.get(['id']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPalletType>>) {
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
