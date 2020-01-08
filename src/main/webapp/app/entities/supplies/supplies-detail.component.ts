import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISupplies } from 'app/shared/model/supplies.model';

@Component({
  selector: 'jhi-supplies-detail',
  templateUrl: './supplies-detail.component.html'
})
export class SuppliesDetailComponent implements OnInit {
  supplies: ISupplies | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ supplies }) => {
      this.supplies = supplies;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
