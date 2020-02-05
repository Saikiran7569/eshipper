import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMonthlyShipments } from 'app/shared/model/monthly-shipments.model';

@Component({
  selector: 'jhi-monthly-shipments-detail',
  templateUrl: './monthly-shipments-detail.component.html'
})
export class MonthlyShipmentsDetailComponent implements OnInit {
  monthlyShipments: IMonthlyShipments | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ monthlyShipments }) => {
      this.monthlyShipments = monthlyShipments;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
