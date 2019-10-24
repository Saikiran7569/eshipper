import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ShipmentPackageComponent } from './shipment-package.component';
import { ShipmentPackageDetailComponent } from './shipment-package-detail.component';
import { ShipmentPackageUpdateComponent } from './shipment-package-update.component';
import { ShipmentPackageDeletePopupComponent, ShipmentPackageDeleteDialogComponent } from './shipment-package-delete-dialog.component';
import { shipmentPackageRoute, shipmentPackagePopupRoute } from './shipment-package.route';

const ENTITY_STATES = [...shipmentPackageRoute, ...shipmentPackagePopupRoute];

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ShipmentPackageComponent,
    ShipmentPackageDetailComponent,
    ShipmentPackageUpdateComponent,
    ShipmentPackageDeleteDialogComponent,
    ShipmentPackageDeletePopupComponent
  ],
  entryComponents: [ShipmentPackageDeleteDialogComponent]
})
export class EshipperShipmentPackageModule {}
