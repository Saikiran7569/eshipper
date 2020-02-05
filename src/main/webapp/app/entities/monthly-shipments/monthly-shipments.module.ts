import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { MonthlyShipmentsComponent } from './monthly-shipments.component';
import { MonthlyShipmentsDetailComponent } from './monthly-shipments-detail.component';
import { MonthlyShipmentsUpdateComponent } from './monthly-shipments-update.component';
import { MonthlyShipmentsDeleteDialogComponent } from './monthly-shipments-delete-dialog.component';
import { monthlyShipmentsRoute } from './monthly-shipments.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(monthlyShipmentsRoute)],
  declarations: [
    MonthlyShipmentsComponent,
    MonthlyShipmentsDetailComponent,
    MonthlyShipmentsUpdateComponent,
    MonthlyShipmentsDeleteDialogComponent
  ],
  entryComponents: [MonthlyShipmentsDeleteDialogComponent]
})
export class EshipperMonthlyShipmentsModule {}
