import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISupplies } from 'app/shared/model/supplies.model';

type EntityResponseType = HttpResponse<ISupplies>;
type EntityArrayResponseType = HttpResponse<ISupplies[]>;

@Injectable({ providedIn: 'root' })
export class SuppliesService {
  public resourceUrl = SERVER_API_URL + 'api/supplies';

  constructor(protected http: HttpClient) {}

  create(supplies: ISupplies): Observable<EntityResponseType> {
    return this.http.post<ISupplies>(this.resourceUrl, supplies, { observe: 'response' });
  }

  update(supplies: ISupplies): Observable<EntityResponseType> {
    return this.http.put<ISupplies>(this.resourceUrl, supplies, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISupplies>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISupplies[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
