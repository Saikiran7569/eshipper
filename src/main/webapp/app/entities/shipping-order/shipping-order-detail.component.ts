import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IShippingOrder } from 'app/shared/model/shipping-order.model';

@Component({
  selector: 'jhi-shipping-order-detail',
  templateUrl: './shipping-order-detail.component.html',
})
export class ShippingOrderDetailComponent implements OnInit {
  shippingOrder: IShippingOrder | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shippingOrder }) => (this.shippingOrder = shippingOrder));
  }

  previousState(): void {
    window.history.back();
  }
}
