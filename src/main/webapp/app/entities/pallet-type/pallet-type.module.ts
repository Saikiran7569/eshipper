import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { PalletTypeComponent } from './pallet-type.component';
import { PalletTypeDetailComponent } from './pallet-type-detail.component';
import { PalletTypeUpdateComponent } from './pallet-type-update.component';
import { PalletTypeDeletePopupComponent, PalletTypeDeleteDialogComponent } from './pallet-type-delete-dialog.component';
import { palletTypeRoute, palletTypePopupRoute } from './pallet-type.route';

const ENTITY_STATES = [...palletTypeRoute, ...palletTypePopupRoute];

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PalletTypeComponent,
    PalletTypeDetailComponent,
    PalletTypeUpdateComponent,
    PalletTypeDeleteDialogComponent,
    PalletTypeDeletePopupComponent
  ],
  entryComponents: [PalletTypeDeleteDialogComponent]
})
export class EshipperPalletTypeModule {}
