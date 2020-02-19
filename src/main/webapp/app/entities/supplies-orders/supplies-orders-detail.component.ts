import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISuppliesOrders } from 'app/shared/model/supplies-orders.model';

@Component({
  selector: 'jhi-supplies-orders-detail',
  templateUrl: './supplies-orders-detail.component.html'
})
export class SuppliesOrdersDetailComponent implements OnInit {
  suppliesOrders: ISuppliesOrders | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ suppliesOrders }) => (this.suppliesOrders = suppliesOrders));
  }

  previousState(): void {
    window.history.back();
  }
}
