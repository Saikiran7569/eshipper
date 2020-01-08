import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { SuppliesComponent } from './supplies.component';
import { SuppliesDetailComponent } from './supplies-detail.component';
import { SuppliesUpdateComponent } from './supplies-update.component';
import { SuppliesDeleteDialogComponent } from './supplies-delete-dialog.component';
import { suppliesRoute } from './supplies.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(suppliesRoute)],
  declarations: [SuppliesComponent, SuppliesDetailComponent, SuppliesUpdateComponent, SuppliesDeleteDialogComponent],
  entryComponents: [SuppliesDeleteDialogComponent]
})
export class EshipperSuppliesModule {}
