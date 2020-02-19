import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { User11Component } from './user-11.component';
import { User11DetailComponent } from './user-11-detail.component';
import { User11UpdateComponent } from './user-11-update.component';
import { User11DeleteDialogComponent } from './user-11-delete-dialog.component';
import { user11Route } from './user-11.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(user11Route)],
  declarations: [User11Component, User11DetailComponent, User11UpdateComponent, User11DeleteDialogComponent],
  entryComponents: [User11DeleteDialogComponent]
})
export class EshipperUser11Module {}
