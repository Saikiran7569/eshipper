import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IBox, Box } from 'app/shared/model/box.model';
import { BoxService } from './box.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IMetric } from 'app/shared/model/metric.model';
import { MetricService } from 'app/entities/metric/metric.service';
import { IWoPackageType } from 'app/shared/model/wo-package-type.model';
import { WoPackageTypeService } from 'app/entities/wo-package-type/wo-package-type.service';

@Component({
  selector: 'jhi-box-update',
  templateUrl: './box-update.component.html'
})
export class BoxUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  metrics: IMetric[];

  wopackagetypes: IWoPackageType[];
  createdDateDp: any;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]],
    description: [null, [Validators.maxLength(255)]],
    maxSupportWeight: [null, [Validators.max(20)]],
    length: [null, [Validators.max(20)]],
    width: [null, [Validators.max(20)]],
    height: [null, [Validators.max(20)]],
    weight: [null, [Validators.max(20)]],
    createdDate: [],
    createdByUserId: [],
    metricId: [],
    woPackageTypeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected boxService: BoxService,
    protected userService: UserService,
    protected metricService: MetricService,
    protected woPackageTypeService: WoPackageTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ box }) => {
      this.updateForm(box);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.metricService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMetric[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMetric[]>) => response.body)
      )
      .subscribe((res: IMetric[]) => (this.metrics = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.woPackageTypeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IWoPackageType[]>) => mayBeOk.ok),
        map((response: HttpResponse<IWoPackageType[]>) => response.body)
      )
      .subscribe((res: IWoPackageType[]) => (this.wopackagetypes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(box: IBox) {
    this.editForm.patchValue({
      id: box.id,
      name: box.name,
      description: box.description,
      maxSupportWeight: box.maxSupportWeight,
      length: box.length,
      width: box.width,
      height: box.height,
      weight: box.weight,
      createdDate: box.createdDate,
      createdByUserId: box.createdByUserId,
      metricId: box.metricId,
      woPackageTypeId: box.woPackageTypeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const box = this.createFromForm();
    if (box.id !== undefined) {
      this.subscribeToSaveResponse(this.boxService.update(box));
    } else {
      this.subscribeToSaveResponse(this.boxService.create(box));
    }
  }

  private createFromForm(): IBox {
    return {
      ...new Box(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      maxSupportWeight: this.editForm.get(['maxSupportWeight']).value,
      length: this.editForm.get(['length']).value,
      width: this.editForm.get(['width']).value,
      height: this.editForm.get(['height']).value,
      weight: this.editForm.get(['weight']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      createdByUserId: this.editForm.get(['createdByUserId']).value,
      metricId: this.editForm.get(['metricId']).value,
      woPackageTypeId: this.editForm.get(['woPackageTypeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBox>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackUserById(index: number, item: IUser) {
    return item.id;
  }

  trackMetricById(index: number, item: IMetric) {
    return item.id;
  }

  trackWoPackageTypeById(index: number, item: IWoPackageType) {
    return item.id;
  }
}
