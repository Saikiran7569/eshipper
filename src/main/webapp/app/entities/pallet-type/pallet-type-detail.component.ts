import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPalletType } from 'app/shared/model/pallet-type.model';

@Component({
  selector: 'jhi-pallet-type-detail',
  templateUrl: './pallet-type-detail.component.html'
})
export class PalletTypeDetailComponent implements OnInit {
  palletType: IPalletType;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ palletType }) => {
      this.palletType = palletType;
    });
  }

  previousState() {
    window.history.back();
  }
}
