import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IShipmentPackage, ShipmentPackage } from 'app/shared/model/shipment-package.model';
import { ShipmentPackageService } from './shipment-package.service';
import { IPalletType } from 'app/shared/model/pallet-type.model';
import { PalletTypeService } from 'app/entities/pallet-type/pallet-type.service';

@Component({
  selector: 'jhi-shipment-package-update',
  templateUrl: './shipment-package-update.component.html'
})
export class ShipmentPackageUpdateComponent implements OnInit {
  isSaving: boolean;

  pallettypes: IPalletType[];

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.maxLength(255)]],
    length: [null, [Validators.max(11)]],
    width: [null, [Validators.max(11)]],
    height: [null, [Validators.max(11)]],
    weight: [null, [Validators.max(11)]],
    position: [null, [Validators.max(11)]],
    trackingNumber: [null, [Validators.maxLength(255)]],
    cubedWeight: [null, [Validators.max(11)]],
    codValue: [],
    insuranceAmount: [],
    freightClass: [null, [Validators.maxLength(10)]],
    nmfcCode: [null, [Validators.maxLength(50)]],
    weightOz: [null, [Validators.max(11)]],
    itemValue: [],
    harmonizedCode: [null, [Validators.maxLength(100)]],
    typeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected shipmentPackageService: ShipmentPackageService,
    protected palletTypeService: PalletTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ shipmentPackage }) => {
      this.updateForm(shipmentPackage);
    });
    this.palletTypeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPalletType[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPalletType[]>) => response.body)
      )
      .subscribe((res: IPalletType[]) => (this.pallettypes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(shipmentPackage: IShipmentPackage) {
    this.editForm.patchValue({
      id: shipmentPackage.id,
      description: shipmentPackage.description,
      length: shipmentPackage.length,
      width: shipmentPackage.width,
      height: shipmentPackage.height,
      weight: shipmentPackage.weight,
      position: shipmentPackage.position,
      trackingNumber: shipmentPackage.trackingNumber,
      cubedWeight: shipmentPackage.cubedWeight,
      codValue: shipmentPackage.codValue,
      insuranceAmount: shipmentPackage.insuranceAmount,
      freightClass: shipmentPackage.freightClass,
      nmfcCode: shipmentPackage.nmfcCode,
      weightOz: shipmentPackage.weightOz,
      itemValue: shipmentPackage.itemValue,
      harmonizedCode: shipmentPackage.harmonizedCode,
      typeId: shipmentPackage.typeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const shipmentPackage = this.createFromForm();
    if (shipmentPackage.id !== undefined) {
      this.subscribeToSaveResponse(this.shipmentPackageService.update(shipmentPackage));
    } else {
      this.subscribeToSaveResponse(this.shipmentPackageService.create(shipmentPackage));
    }
  }

  private createFromForm(): IShipmentPackage {
    return {
      ...new ShipmentPackage(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      length: this.editForm.get(['length']).value,
      width: this.editForm.get(['width']).value,
      height: this.editForm.get(['height']).value,
      weight: this.editForm.get(['weight']).value,
      position: this.editForm.get(['position']).value,
      trackingNumber: this.editForm.get(['trackingNumber']).value,
      cubedWeight: this.editForm.get(['cubedWeight']).value,
      codValue: this.editForm.get(['codValue']).value,
      insuranceAmount: this.editForm.get(['insuranceAmount']).value,
      freightClass: this.editForm.get(['freightClass']).value,
      nmfcCode: this.editForm.get(['nmfcCode']).value,
      weightOz: this.editForm.get(['weightOz']).value,
      itemValue: this.editForm.get(['itemValue']).value,
      harmonizedCode: this.editForm.get(['harmonizedCode']).value,
      typeId: this.editForm.get(['typeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShipmentPackage>>) {
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

  trackPalletTypeById(index: number, item: IPalletType) {
    return item.id;
  }
}
