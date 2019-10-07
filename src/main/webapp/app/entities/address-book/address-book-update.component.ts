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
import { IAddressBook, AddressBook } from 'app/shared/model/address-book.model';
import { AddressBookService } from './address-book.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';
import { IProvince } from 'app/shared/model/province.model';
import { ProvinceService } from 'app/entities/province/province.service';
import { ICity } from 'app/shared/model/city.model';
import { CityService } from 'app/entities/city/city.service';
import { ICompany } from 'app/shared/model/company.model';
import { CompanyService } from 'app/entities/company/company.service';
import { IUser1 } from 'app/shared/model/user-1.model';
import { User1Service } from 'app/entities/user-1/user-1.service';

@Component({
  selector: 'jhi-address-book-update',
  templateUrl: './address-book-update.component.html'
})
export class AddressBookUpdateComponent implements OnInit {
  isSaving: boolean;

  countries: ICountry[];

  provinces: IProvince[];

  cities: ICity[];

  companies: ICompany[];

  user1s: IUser1[];
  dateCreatedDp: any;
  dateUpdatedDp: any;

  editForm = this.fb.group({
    id: [],
    companyName: [null, [Validators.maxLength(255)]],
    address1: [null, [Validators.maxLength(255)]],
    address2: [null, [Validators.maxLength(255)]],
    postalCode: [null, [Validators.maxLength(255)]],
    contactName: [null, [Validators.maxLength(255)]],
    phoneNo: [null, [Validators.maxLength(255)]],
    contactEmail: [null, [Validators.maxLength(255)]],
    notify: [],
    residential: [],
    instruction: [null, [Validators.maxLength(255)]],
    dateCreated: [],
    dateUpdated: [],
    countryId: [],
    provinceId: [],
    cityId: [],
    companyId: [],
    user1Id: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected addressBookService: AddressBookService,
    protected countryService: CountryService,
    protected provinceService: ProvinceService,
    protected cityService: CityService,
    protected companyService: CompanyService,
    protected user1Service: User1Service,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ addressBook }) => {
      this.updateForm(addressBook);
    });
    this.countryService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICountry[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICountry[]>) => response.body)
      )
      .subscribe((res: ICountry[]) => (this.countries = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.provinceService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProvince[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProvince[]>) => response.body)
      )
      .subscribe((res: IProvince[]) => (this.provinces = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.cityService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICity[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICity[]>) => response.body)
      )
      .subscribe((res: ICity[]) => (this.cities = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.companyService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICompany[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICompany[]>) => response.body)
      )
      .subscribe((res: ICompany[]) => (this.companies = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.user1Service
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser1[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser1[]>) => response.body)
      )
      .subscribe((res: IUser1[]) => (this.user1s = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(addressBook: IAddressBook) {
    this.editForm.patchValue({
      id: addressBook.id,
      companyName: addressBook.companyName,
      address1: addressBook.address1,
      address2: addressBook.address2,
      postalCode: addressBook.postalCode,
      contactName: addressBook.contactName,
      phoneNo: addressBook.phoneNo,
      contactEmail: addressBook.contactEmail,
      notify: addressBook.notify,
      residential: addressBook.residential,
      instruction: addressBook.instruction,
      dateCreated: addressBook.dateCreated,
      dateUpdated: addressBook.dateUpdated,
      countryId: addressBook.countryId,
      provinceId: addressBook.provinceId,
      cityId: addressBook.cityId,
      companyId: addressBook.companyId,
      user1Id: addressBook.user1Id
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const addressBook = this.createFromForm();
    if (addressBook.id !== undefined) {
      this.subscribeToSaveResponse(this.addressBookService.update(addressBook));
    } else {
      this.subscribeToSaveResponse(this.addressBookService.create(addressBook));
    }
  }

  private createFromForm(): IAddressBook {
    return {
      ...new AddressBook(),
      id: this.editForm.get(['id']).value,
      companyName: this.editForm.get(['companyName']).value,
      address1: this.editForm.get(['address1']).value,
      address2: this.editForm.get(['address2']).value,
      postalCode: this.editForm.get(['postalCode']).value,
      contactName: this.editForm.get(['contactName']).value,
      phoneNo: this.editForm.get(['phoneNo']).value,
      contactEmail: this.editForm.get(['contactEmail']).value,
      notify: this.editForm.get(['notify']).value,
      residential: this.editForm.get(['residential']).value,
      instruction: this.editForm.get(['instruction']).value,
      dateCreated: this.editForm.get(['dateCreated']).value,
      dateUpdated: this.editForm.get(['dateUpdated']).value,
      countryId: this.editForm.get(['countryId']).value,
      provinceId: this.editForm.get(['provinceId']).value,
      cityId: this.editForm.get(['cityId']).value,
      companyId: this.editForm.get(['companyId']).value,
      user1Id: this.editForm.get(['user1Id']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAddressBook>>) {
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

  trackCountryById(index: number, item: ICountry) {
    return item.id;
  }

  trackProvinceById(index: number, item: IProvince) {
    return item.id;
  }

  trackCityById(index: number, item: ICity) {
    return item.id;
  }

  trackCompanyById(index: number, item: ICompany) {
    return item.id;
  }

  trackUser1ById(index: number, item: IUser1) {
    return item.id;
  }
}
