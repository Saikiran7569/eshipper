import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMetric, Metric } from 'app/shared/model/metric.model';
import { MetricService } from './metric.service';

@Component({
  selector: 'jhi-metric-update',
  templateUrl: './metric-update.component.html'
})
export class MetricUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: []
  });

  constructor(protected metricService: MetricService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ metric }) => {
      this.updateForm(metric);
    });
  }

  updateForm(metric: IMetric) {
    this.editForm.patchValue({
      id: metric.id
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const metric = this.createFromForm();
    if (metric.id !== undefined) {
      this.subscribeToSaveResponse(this.metricService.update(metric));
    } else {
      this.subscribeToSaveResponse(this.metricService.create(metric));
    }
  }

  private createFromForm(): IMetric {
    return {
      ...new Metric(),
      id: this.editForm.get(['id']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMetric>>) {
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
