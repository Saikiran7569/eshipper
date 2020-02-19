import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { SuppliesOrdersComponent } from './supplies-orders.component';
import { SuppliesOrdersDetailComponent } from './supplies-orders-detail.component';
import { SuppliesOrdersUpdateComponent } from './supplies-orders-update.component';
import { SuppliesOrdersDeleteDialogComponent } from './supplies-orders-delete-dialog.component';
import { suppliesOrdersRoute } from './supplies-orders.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(suppliesOrdersRoute)],
  declarations: [
    SuppliesOrdersComponent,
    SuppliesOrdersDetailComponent,
    SuppliesOrdersUpdateComponent,
    SuppliesOrdersDeleteDialogComponent
  ],
  entryComponents: [SuppliesOrdersDeleteDialogComponent]
})
export class EshipperSuppliesOrdersModule {}
