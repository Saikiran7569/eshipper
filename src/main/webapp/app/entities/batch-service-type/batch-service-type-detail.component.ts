import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBatchServiceType } from 'app/shared/model/batch-service-type.model';

@Component({
  selector: 'jhi-batch-service-type-detail',
  templateUrl: './batch-service-type-detail.component.html'
})
export class BatchServiceTypeDetailComponent implements OnInit {
  batchServiceType: IBatchServiceType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ batchServiceType }) => {
      this.batchServiceType = batchServiceType;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
