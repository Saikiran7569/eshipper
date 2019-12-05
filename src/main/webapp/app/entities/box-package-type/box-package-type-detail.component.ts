import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBoxPackageType } from 'app/shared/model/box-package-type.model';

@Component({
  selector: 'jhi-box-package-type-detail',
  templateUrl: './box-package-type-detail.component.html'
})
export class BoxPackageTypeDetailComponent implements OnInit {
  boxPackageType: IBoxPackageType;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ boxPackageType }) => {
      this.boxPackageType = boxPackageType;
    });
  }

  previousState() {
    window.history.back();
  }
}
