import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISuppliesOrders } from 'app/shared/model/supplies-orders.model';

type EntityResponseType = HttpResponse<ISuppliesOrders>;
type EntityArrayResponseType = HttpResponse<ISuppliesOrders[]>;

@Injectable({ providedIn: 'root' })
export class SuppliesOrdersService {
  public resourceUrl = SERVER_API_URL + 'api/supplies-orders';

  constructor(protected http: HttpClient) {}

  create(suppliesOrders: ISuppliesOrders): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(suppliesOrders);
    return this.http
      .post<ISuppliesOrders>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(suppliesOrders: ISuppliesOrders): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(suppliesOrders);
    return this.http
      .put<ISuppliesOrders>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISuppliesOrders>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISuppliesOrders[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(suppliesOrders: ISuppliesOrders): ISuppliesOrders {
    const copy: ISuppliesOrders = Object.assign({}, suppliesOrders, {
      createdDate:
        suppliesOrders.createdDate && suppliesOrders.createdDate.isValid() ? suppliesOrders.createdDate.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((suppliesOrders: ISuppliesOrders) => {
        suppliesOrders.createdDate = suppliesOrders.createdDate ? moment(suppliesOrders.createdDate) : undefined;
      });
    }
    return res;
  }
}
