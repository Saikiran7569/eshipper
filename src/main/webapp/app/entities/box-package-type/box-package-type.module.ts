import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { BoxPackageTypeComponent } from './box-package-type.component';
import { BoxPackageTypeDetailComponent } from './box-package-type-detail.component';
import { BoxPackageTypeUpdateComponent } from './box-package-type-update.component';
import { BoxPackageTypeDeleteDialogComponent } from './box-package-type-delete-dialog.component';
import { boxPackageTypeRoute } from './box-package-type.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(boxPackageTypeRoute)],
  declarations: [
    BoxPackageTypeComponent,
    BoxPackageTypeDetailComponent,
    BoxPackageTypeUpdateComponent,
    BoxPackageTypeDeleteDialogComponent
  ],
  entryComponents: [BoxPackageTypeDeleteDialogComponent]
})
export class EshipperBoxPackageTypeModule {}
