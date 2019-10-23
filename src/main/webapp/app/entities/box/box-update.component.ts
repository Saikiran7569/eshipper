import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IBox, Box } from 'app/shared/model/box.model';
import { BoxService } from './box.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IMetric } from 'app/shared/model/metric.model';
import { MetricService } from 'app/entities/metric/metric.service';
import { IPalletType } from 'app/shared/model/pallet-type.model';
import { PalletTypeService } from 'app/entities/pallet-type/pallet-type.service';
import { ICompany } from 'app/shared/model/company.model';
import { CompanyService } from 'app/entities/company/company.service';

@Component({
  selector: 'jhi-box-update',
  templateUrl: './box-update.component.html'
})
export class BoxUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  metrics: IMetric[];

  pallettypes: IPalletType[];

  companies: ICompany[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]],
    description: [null, [Validators.maxLength(255)]],
    maxSupportWeight: [],
    length: [],
    width: [],
    height: [],
    weight: [],
    dateCreated: [],
    dateUpdated: [],
    createdByUserId: [],
    metricId: [],
    palletTypeId: [],
    companyId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected boxService: BoxService,
    protected userService: UserService,
    protected metricService: MetricService,
    protected palletTypeService: PalletTypeService,
    protected companyService: CompanyService,
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
    this.palletTypeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPalletType[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPalletType[]>) => response.body)
      )
      .subscribe((res: IPalletType[]) => (this.pallettypes = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.companyService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICompany[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICompany[]>) => response.body)
      )
      .subscribe((res: ICompany[]) => (this.companies = res), (res: HttpErrorResponse) => this.onError(res.message));
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
      dateCreated: box.dateCreated != null ? box.dateCreated.format(DATE_TIME_FORMAT) : null,
      dateUpdated: box.dateUpdated != null ? box.dateUpdated.format(DATE_TIME_FORMAT) : null,
      createdByUserId: box.createdByUserId,
      metricId: box.metricId,
      palletTypeId: box.palletTypeId,
      companyId: box.companyId
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
      dateCreated:
        this.editForm.get(['dateCreated']).value != null ? moment(this.editForm.get(['dateCreated']).value, DATE_TIME_FORMAT) : undefined,
      dateUpdated:
        this.editForm.get(['dateUpdated']).value != null ? moment(this.editForm.get(['dateUpdated']).value, DATE_TIME_FORMAT) : undefined,
      createdByUserId: this.editForm.get(['createdByUserId']).value,
      metricId: this.editForm.get(['metricId']).value,
      palletTypeId: this.editForm.get(['palletTypeId']).value,
      companyId: this.editForm.get(['companyId']).value
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

  trackPalletTypeById(index: number, item: IPalletType) {
    return item.id;
  }

  trackCompanyById(index: number, item: ICompany) {
    return item.id;
  }
}
