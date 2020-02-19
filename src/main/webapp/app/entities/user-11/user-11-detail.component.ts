import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUser11 } from 'app/shared/model/user-11.model';

@Component({
  selector: 'jhi-user-11-detail',
  templateUrl: './user-11-detail.component.html'
})
export class User11DetailComponent implements OnInit {
  user11: IUser11 | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ user11 }) => (this.user11 = user11));
  }

  previousState(): void {
    window.history.back();
  }
}
