import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { AddressBookComponent } from './address-book.component';
import { AddressBookDetailComponent } from './address-book-detail.component';
import { AddressBookUpdateComponent } from './address-book-update.component';
import { AddressBookDeletePopupComponent, AddressBookDeleteDialogComponent } from './address-book-delete-dialog.component';
import { addressBookRoute, addressBookPopupRoute } from './address-book.route';

const ENTITY_STATES = [...addressBookRoute, ...addressBookPopupRoute];

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AddressBookComponent,
    AddressBookDetailComponent,
    AddressBookUpdateComponent,
    AddressBookDeleteDialogComponent,
    AddressBookDeletePopupComponent
  ],
  entryComponents: [AddressBookDeleteDialogComponent]
})
export class EshipperAddressBookModule {}
