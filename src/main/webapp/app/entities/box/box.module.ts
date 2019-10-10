import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { BoxComponent } from './box.component';
import { BoxDetailComponent } from './box-detail.component';
import { BoxUpdateComponent } from './box-update.component';
import { BoxDeletePopupComponent, BoxDeleteDialogComponent } from './box-delete-dialog.component';
import { boxRoute, boxPopupRoute } from './box.route';

const ENTITY_STATES = [...boxRoute, ...boxPopupRoute];

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [BoxComponent, BoxDetailComponent, BoxUpdateComponent, BoxDeleteDialogComponent, BoxDeletePopupComponent],
  entryComponents: [BoxDeleteDialogComponent]
})
export class EshipperBoxModule {}
