import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IShipmentPackage } from 'app/shared/model/shipment-package.model';

@Component({
  selector: 'jhi-shipment-package-detail',
  templateUrl: './shipment-package-detail.component.html'
})
export class ShipmentPackageDetailComponent implements OnInit {
  shipmentPackage: IShipmentPackage;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ shipmentPackage }) => {
      this.shipmentPackage = shipmentPackage;
    });
  }

  previousState() {
    window.history.back();
  }
}
