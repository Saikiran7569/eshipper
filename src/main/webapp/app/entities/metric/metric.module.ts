import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { MetricComponent } from './metric.component';
import { MetricDetailComponent } from './metric-detail.component';
import { MetricUpdateComponent } from './metric-update.component';
import { MetricDeletePopupComponent, MetricDeleteDialogComponent } from './metric-delete-dialog.component';
import { metricRoute, metricPopupRoute } from './metric.route';

const ENTITY_STATES = [...metricRoute, ...metricPopupRoute];

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MetricComponent, MetricDetailComponent, MetricUpdateComponent, MetricDeleteDialogComponent, MetricDeletePopupComponent],
  entryComponents: [MetricDeleteDialogComponent]
})
export class EshipperMetricModule {}
