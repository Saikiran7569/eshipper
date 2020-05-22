import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { CommodityTypeComponent } from './commodity-type.component';
import { CommodityTypeDetailComponent } from './commodity-type-detail.component';
import { CommodityTypeUpdateComponent } from './commodity-type-update.component';
import { CommodityTypeDeleteDialogComponent } from './commodity-type-delete-dialog.component';
import { commodityTypeRoute } from './commodity-type.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(commodityTypeRoute)],
  declarations: [CommodityTypeComponent, CommodityTypeDetailComponent, CommodityTypeUpdateComponent, CommodityTypeDeleteDialogComponent],
  entryComponents: [CommodityTypeDeleteDialogComponent],
})
export class EshipperCommodityTypeModule {}
