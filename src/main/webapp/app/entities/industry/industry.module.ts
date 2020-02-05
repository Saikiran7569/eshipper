import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { IndustryComponent } from './industry.component';
import { IndustryDetailComponent } from './industry-detail.component';
import { IndustryUpdateComponent } from './industry-update.component';
import { IndustryDeleteDialogComponent } from './industry-delete-dialog.component';
import { industryRoute } from './industry.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(industryRoute)],
  declarations: [IndustryComponent, IndustryDetailComponent, IndustryUpdateComponent, IndustryDeleteDialogComponent],
  entryComponents: [IndustryDeleteDialogComponent]
})
export class EshipperIndustryModule {}
