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
      },
      {
        path: 'user-1',
        loadChildren: () => import('./user-1/user-1.module').then(m => m.EshipperUser1Module)
      },
      {
        path: 'box',
        loadChildren: () => import('./box/box.module').then(m => m.EshipperBoxModule)
      },
      {
        path: 'metric',
        loadChildren: () => import('./metric/metric.module').then(m => m.EshipperMetricModule)
      },
      {
        path: 'wo-package-type',
        loadChildren: () => import('./wo-package-type/wo-package-type.module').then(m => m.EshipperWoPackageTypeModule)
      },
      {
        path: 'pallet-type',
        loadChildren: () => import('./pallet-type/pallet-type.module').then(m => m.EshipperPalletTypeModule)
      },
      {
        path: 'shipment-package',
        loadChildren: () => import('./shipment-package/shipment-package.module').then(m => m.EshipperShipmentPackageModule)
      },
      {
        path: 'box-package-type',
        loadChildren: () => import('./box-package-type/box-package-type.module').then(m => m.EshipperBoxPackageTypeModule)
      },
      {
        path: 'supplies',
        loadChildren: () => import('./supplies/supplies.module').then(m => m.EshipperSuppliesModule)
      },
      {
        path: 'carrier',
        loadChildren: () => import('./carrier/carrier.module').then(m => m.EshipperCarrierModule)
      },
      {
        path: 'industry',
        loadChildren: () => import('./industry/industry.module').then(m => m.EshipperIndustryModule)
      },
      {
        path: 'monthly-shipments',
        loadChildren: () => import('./monthly-shipments/monthly-shipments.module').then(m => m.EshipperMonthlyShipmentsModule)
      },
      {
        path: 'customs-tax-bill-to',
        loadChildren: () => import('./customs-tax-bill-to/customs-tax-bill-to.module').then(m => m.EshipperCustomsTaxBillToModule)
      },
      {
        path: 'batch-service-type',
        loadChildren: () => import('./batch-service-type/batch-service-type.module').then(m => m.EshipperBatchServiceTypeModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class EshipperEntityModule {}
