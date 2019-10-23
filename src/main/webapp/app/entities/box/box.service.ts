import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBox } from 'app/shared/model/box.model';

type EntityResponseType = HttpResponse<IBox>;
type EntityArrayResponseType = HttpResponse<IBox[]>;

@Injectable({ providedIn: 'root' })
export class BoxService {
  public resourceUrl = SERVER_API_URL + 'api/boxes';

  constructor(protected http: HttpClient) {}

  create(box: IBox): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(box);
    return this.http
      .post<IBox>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(box: IBox): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(box);
    return this.http
      .put<IBox>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBox>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBox[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(box: IBox): IBox {
    const copy: IBox = Object.assign({}, box, {
      dateCreated: box.dateCreated != null && box.dateCreated.isValid() ? box.dateCreated.toJSON() : null,
      dateUpdated: box.dateUpdated != null && box.dateUpdated.isValid() ? box.dateUpdated.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateCreated = res.body.dateCreated != null ? moment(res.body.dateCreated) : null;
      res.body.dateUpdated = res.body.dateUpdated != null ? moment(res.body.dateUpdated) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((box: IBox) => {
        box.dateCreated = box.dateCreated != null ? moment(box.dateCreated) : null;
        box.dateUpdated = box.dateUpdated != null ? moment(box.dateUpdated) : null;
      });
    }
    return res;
  }
}
