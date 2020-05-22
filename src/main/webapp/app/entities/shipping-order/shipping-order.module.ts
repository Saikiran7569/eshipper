import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ShippingOrderComponent } from './shipping-order.component';
import { ShippingOrderDetailComponent } from './shipping-order-detail.component';
import { ShippingOrderUpdateComponent } from './shipping-order-update.component';
import { ShippingOrderDeleteDialogComponent } from './shipping-order-delete-dialog.component';
import { shippingOrderRoute } from './shipping-order.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(shippingOrderRoute)],
  declarations: [ShippingOrderComponent, ShippingOrderDetailComponent, ShippingOrderUpdateComponent, ShippingOrderDeleteDialogComponent],
  entryComponents: [ShippingOrderDeleteDialogComponent],
})
export class EshipperShippingOrderModule {}
