import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPalletType } from 'app/shared/model/pallet-type.model';

type EntityResponseType = HttpResponse<IPalletType>;
type EntityArrayResponseType = HttpResponse<IPalletType[]>;

@Injectable({ providedIn: 'root' })
export class PalletTypeService {
  public resourceUrl = SERVER_API_URL + 'api/pallet-types';

  constructor(protected http: HttpClient) {}

  create(palletType: IPalletType): Observable<EntityResponseType> {
    return this.http.post<IPalletType>(this.resourceUrl, palletType, { observe: 'response' });
  }

  update(palletType: IPalletType): Observable<EntityResponseType> {
    return this.http.put<IPalletType>(this.resourceUrl, palletType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPalletType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPalletType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
