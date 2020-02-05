import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { CustomsTaxBillToComponent } from './customs-tax-bill-to.component';
import { CustomsTaxBillToDetailComponent } from './customs-tax-bill-to-detail.component';
import { CustomsTaxBillToUpdateComponent } from './customs-tax-bill-to-update.component';
import { CustomsTaxBillToDeleteDialogComponent } from './customs-tax-bill-to-delete-dialog.component';
import { customsTaxBillToRoute } from './customs-tax-bill-to.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(customsTaxBillToRoute)],
  declarations: [
    CustomsTaxBillToComponent,
    CustomsTaxBillToDetailComponent,
    CustomsTaxBillToUpdateComponent,
    CustomsTaxBillToDeleteDialogComponent
  ],
  entryComponents: [CustomsTaxBillToDeleteDialogComponent]
})
export class EshipperCustomsTaxBillToModule {}
