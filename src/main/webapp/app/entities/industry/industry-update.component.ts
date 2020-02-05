import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IIndustry, Industry } from 'app/shared/model/industry.model';
import { IndustryService } from './industry.service';

@Component({
  selector: 'jhi-industry-update',
  templateUrl: './industry-update.component.html'
})
export class IndustryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: []
  });

  constructor(protected industryService: IndustryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ industry }) => {
      this.updateForm(industry);
    });
  }

  updateForm(industry: IIndustry): void {
    this.editForm.patchValue({
      id: industry.id,
      name: industry.name
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const industry = this.createFromForm();
    if (industry.id !== undefined) {
      this.subscribeToSaveResponse(this.industryService.update(industry));
    } else {
      this.subscribeToSaveResponse(this.industryService.create(industry));
    }
  }

  private createFromForm(): IIndustry {
    return {
      ...new Industry(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIndustry>>): void {
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
