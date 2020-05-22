import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICommodityType } from 'app/shared/model/commodity-type.model';

type EntityResponseType = HttpResponse<ICommodityType>;
type EntityArrayResponseType = HttpResponse<ICommodityType[]>;

@Injectable({ providedIn: 'root' })
export class CommodityTypeService {
  public resourceUrl = SERVER_API_URL + 'api/commodity-types';

  constructor(protected http: HttpClient) {}

  create(commodityType: ICommodityType): Observable<EntityResponseType> {
    return this.http.post<ICommodityType>(this.resourceUrl, commodityType, { observe: 'response' });
  }

  update(commodityType: ICommodityType): Observable<EntityResponseType> {
    return this.http.put<ICommodityType>(this.resourceUrl, commodityType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICommodityType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICommodityType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
