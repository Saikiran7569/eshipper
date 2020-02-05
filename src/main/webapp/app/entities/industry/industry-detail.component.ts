import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIndustry } from 'app/shared/model/industry.model';

@Component({
  selector: 'jhi-industry-detail',
  templateUrl: './industry-detail.component.html'
})
export class IndustryDetailComponent implements OnInit {
  industry: IIndustry | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ industry }) => {
      this.industry = industry;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
