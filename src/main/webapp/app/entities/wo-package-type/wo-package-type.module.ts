import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { WoPackageTypeComponent } from './wo-package-type.component';
import { WoPackageTypeDetailComponent } from './wo-package-type-detail.component';
import { WoPackageTypeUpdateComponent } from './wo-package-type-update.component';
import { WoPackageTypeDeletePopupComponent, WoPackageTypeDeleteDialogComponent } from './wo-package-type-delete-dialog.component';
import { woPackageTypeRoute, woPackageTypePopupRoute } from './wo-package-type.route';

const ENTITY_STATES = [...woPackageTypeRoute, ...woPackageTypePopupRoute];

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    WoPackageTypeComponent,
    WoPackageTypeDetailComponent,
    WoPackageTypeUpdateComponent,
    WoPackageTypeDeleteDialogComponent,
    WoPackageTypeDeletePopupComponent
  ],
  entryComponents: [WoPackageTypeDeleteDialogComponent]
})
export class EshipperWoPackageTypeModule {}
