import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUser11, User11 } from 'app/shared/model/user-11.model';
import { User11Service } from './user-11.service';

@Component({
  selector: 'jhi-user-11-update',
  templateUrl: './user-11-update.component.html'
})
export class User11UpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: []
  });

  constructor(protected user11Service: User11Service, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ user11 }) => {
      this.updateForm(user11);
    });
  }

  updateForm(user11: IUser11): void {
    this.editForm.patchValue({
      id: user11.id
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const user11 = this.createFromForm();
    if (user11.id !== undefined) {
      this.subscribeToSaveResponse(this.user11Service.update(user11));
    } else {
      this.subscribeToSaveResponse(this.user11Service.create(user11));
    }
  }

  private createFromForm(): IUser11 {
    return {
      ...new User11(),
      id: this.editForm.get(['id'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUser11>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
