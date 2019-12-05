import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { BoxComponent } from './box.component';
import { BoxDetailComponent } from './box-detail.component';
import { BoxUpdateComponent } from './box-update.component';
import { BoxDeleteDialogComponent } from './box-delete-dialog.component';
import { boxRoute } from './box.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(boxRoute)],
  declarations: [BoxComponent, BoxDetailComponent, BoxUpdateComponent, BoxDeleteDialogComponent],
  entryComponents: [BoxDeleteDialogComponent]
})
export class EshipperBoxModule {}
