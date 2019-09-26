import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProvince, Province } from 'app/shared/model/province.model';
import { ProvinceService } from './province.service';

@Component({
  selector: 'jhi-province-update',
  templateUrl: './province-update.component.html'
})
export class ProvinceUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]],
    fullName: [null, [Validators.maxLength(255)]]
  });

  constructor(protected provinceService: ProvinceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ province }) => {
      this.updateForm(province);
    });
  }

  updateForm(province: IProvince) {
    this.editForm.patchValue({
      id: province.id,
      name: province.name,
      fullName: province.fullName
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const province = this.createFromForm();
    if (province.id !== undefined) {
      this.subscribeToSaveResponse(this.provinceService.update(province));
    } else {
      this.subscribeToSaveResponse(this.provinceService.create(province));
    }
  }

  private createFromForm(): IProvince {
    return {
      ...new Province(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      fullName: this.editForm.get(['fullName']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProvince>>) {
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
