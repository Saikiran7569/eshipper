import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IBoxPackageType, BoxPackageType } from 'app/shared/model/box-package-type.model';
import { BoxPackageTypeService } from './box-package-type.service';

@Component({
  selector: 'jhi-box-package-type-update',
  templateUrl: './box-package-type-update.component.html'
})
export class BoxPackageTypeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: []
  });

  constructor(protected boxPackageTypeService: BoxPackageTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ boxPackageType }) => {
      this.updateForm(boxPackageType);
    });
  }

  updateForm(boxPackageType: IBoxPackageType) {
    this.editForm.patchValue({
      id: boxPackageType.id,
      name: boxPackageType.name
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const boxPackageType = this.createFromForm();
    if (boxPackageType.id !== undefined) {
      this.subscribeToSaveResponse(this.boxPackageTypeService.update(boxPackageType));
    } else {
      this.subscribeToSaveResponse(this.boxPackageTypeService.create(boxPackageType));
    }
  }

  private createFromForm(): IBoxPackageType {
    return {
      ...new BoxPackageType(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBoxPackageType>>) {
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
