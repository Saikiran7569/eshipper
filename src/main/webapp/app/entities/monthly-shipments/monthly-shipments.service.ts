import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMonthlyShipments } from 'app/shared/model/monthly-shipments.model';

type EntityResponseType = HttpResponse<IMonthlyShipments>;
type EntityArrayResponseType = HttpResponse<IMonthlyShipments[]>;

@Injectable({ providedIn: 'root' })
export class MonthlyShipmentsService {
  public resourceUrl = SERVER_API_URL + 'api/monthly-shipments';

  constructor(protected http: HttpClient) {}

  create(monthlyShipments: IMonthlyShipments): Observable<EntityResponseType> {
    return this.http.post<IMonthlyShipments>(this.resourceUrl, monthlyShipments, { observe: 'response' });
  }

  update(monthlyShipments: IMonthlyShipments): Observable<EntityResponseType> {
    return this.http.put<IMonthlyShipments>(this.resourceUrl, monthlyShipments, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMonthlyShipments>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMonthlyShipments[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
