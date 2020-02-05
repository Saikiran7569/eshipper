import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustomsTaxBillTo } from 'app/shared/model/customs-tax-bill-to.model';

@Component({
  selector: 'jhi-customs-tax-bill-to-detail',
  templateUrl: './customs-tax-bill-to-detail.component.html'
})
export class CustomsTaxBillToDetailComponent implements OnInit {
  customsTaxBillTo: ICustomsTaxBillTo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ customsTaxBillTo }) => {
      this.customsTaxBillTo = customsTaxBillTo;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
