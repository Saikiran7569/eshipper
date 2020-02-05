import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICompany, Company } from 'app/shared/model/company.model';
import { CompanyService } from './company.service';
import { IIndustry } from 'app/shared/model/industry.model';
import { IndustryService } from 'app/entities/industry/industry.service';
import { IMonthlyShipments } from 'app/shared/model/monthly-shipments.model';
import { MonthlyShipmentsService } from 'app/entities/monthly-shipments/monthly-shipments.service';

type SelectableEntity = IIndustry | IMonthlyShipments;

@Component({
  selector: 'jhi-company-update',
  templateUrl: './company-update.component.html'
})
export class CompanyUpdateComponent implements OnInit {
  isSaving = false;

  industries: IIndustry[] = [];

  monthlyshipments: IMonthlyShipments[] = [];

  editForm = this.fb.group({
    id: [],
    industryId: [],
    monthlyshipmentsId: []
  });

  constructor(
    protected companyService: CompanyService,
    protected industryService: IndustryService,
    protected monthlyShipmentsService: MonthlyShipmentsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ company }) => {
      this.updateForm(company);

      this.industryService
        .query()
        .pipe(
          map((res: HttpResponse<IIndustry[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IIndustry[]) => (this.industries = resBody));

      this.monthlyShipmentsService
        .query()
        .pipe(
          map((res: HttpResponse<IMonthlyShipments[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IMonthlyShipments[]) => (this.monthlyshipments = resBody));
    });
  }

  updateForm(company: ICompany): void {
    this.editForm.patchValue({
      id: company.id,
      industryId: company.industryId,
      monthlyshipmentsId: company.monthlyshipmentsId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const company = this.createFromForm();
    if (company.id !== undefined) {
      this.subscribeToSaveResponse(this.companyService.update(company));
    } else {
      this.subscribeToSaveResponse(this.companyService.create(company));
    }
  }

  private createFromForm(): ICompany {
    return {
      ...new Company(),
      id: this.editForm.get(['id'])!.value,
      industryId: this.editForm.get(['industryId'])!.value,
      monthlyshipmentsId: this.editForm.get(['monthlyshipmentsId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompany>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
