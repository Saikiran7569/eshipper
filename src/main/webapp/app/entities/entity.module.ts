import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'address-book',
        loadChildren: () => import('./address-book/address-book.module').then(m => m.EshipperAddressBookModule)
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.EshipperCountryModule)
      },
      {
        path: 'customer',
        loadChildren: () => import('./customer/customer.module').then(m => m.EshipperCustomerModule)
      },
      {
        path: 'province',
        loadChildren: () => import('./province/province.module').then(m => m.EshipperProvinceModule)
      },
      {
        path: 'city',
        loadChildren: () => import('./city/city.module').then(m => m.EshipperCityModule)
      },
      {
        path: 'company',
        loadChildren: () => import('./company/company.module').then(m => m.EshipperCompanyModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class EshipperEntityModule {}
