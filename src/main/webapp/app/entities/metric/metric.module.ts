import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { MetricComponent } from './metric.component';
import { MetricDetailComponent } from './metric-detail.component';
import { MetricUpdateComponent } from './metric-update.component';
import { MetricDeleteDialogComponent } from './metric-delete-dialog.component';
import { metricRoute } from './metric.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(metricRoute)],
  declarations: [MetricComponent, MetricDetailComponent, MetricUpdateComponent, MetricDeleteDialogComponent],
  entryComponents: [MetricDeleteDialogComponent]
})
export class EshipperMetricModule {}
