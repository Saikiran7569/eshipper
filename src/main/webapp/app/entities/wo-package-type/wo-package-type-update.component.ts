import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IWoPackageType, WoPackageType } from 'app/shared/model/wo-package-type.model';
import { WoPackageTypeService } from './wo-package-type.service';

@Component({
  selector: 'jhi-wo-package-type-update',
  templateUrl: './wo-package-type-update.component.html'
})
export class WoPackageTypeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: []
  });

  constructor(protected woPackageTypeService: WoPackageTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ woPackageType }) => {
      this.updateForm(woPackageType);
    });
  }

  updateForm(woPackageType: IWoPackageType) {
    this.editForm.patchValue({
      id: woPackageType.id
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const woPackageType = this.createFromForm();
    if (woPackageType.id !== undefined) {
      this.subscribeToSaveResponse(this.woPackageTypeService.update(woPackageType));
    } else {
      this.subscribeToSaveResponse(this.woPackageTypeService.create(woPackageType));
    }
  }

  private createFromForm(): IWoPackageType {
    return {
      ...new WoPackageType(),
      id: this.editForm.get(['id']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoPackageType>>) {
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
