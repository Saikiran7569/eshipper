import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { SupplyComponent } from './supply.component';
import { SupplyDetailComponent } from './supply-detail.component';
import { SupplyUpdateComponent } from './supply-update.component';
import { SupplyDeleteDialogComponent } from './supply-delete-dialog.component';
import { supplyRoute } from './supply.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(supplyRoute)],
  declarations: [SupplyComponent, SupplyDetailComponent, SupplyUpdateComponent, SupplyDeleteDialogComponent],
  entryComponents: [SupplyDeleteDialogComponent]
})
export class EshipperSupplyModule {}
