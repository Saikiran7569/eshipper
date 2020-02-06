import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { BatchServiceTypeComponent } from './batch-service-type.component';
import { BatchServiceTypeDetailComponent } from './batch-service-type-detail.component';
import { BatchServiceTypeUpdateComponent } from './batch-service-type-update.component';
import { BatchServiceTypeDeleteDialogComponent } from './batch-service-type-delete-dialog.component';
import { batchServiceTypeRoute } from './batch-service-type.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(batchServiceTypeRoute)],
  declarations: [
    BatchServiceTypeComponent,
    BatchServiceTypeDetailComponent,
    BatchServiceTypeUpdateComponent,
    BatchServiceTypeDeleteDialogComponent
  ],
  entryComponents: [BatchServiceTypeDeleteDialogComponent]
})
export class EshipperBatchServiceTypeModule {}
