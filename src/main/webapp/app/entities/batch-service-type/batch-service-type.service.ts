import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBatchServiceType } from 'app/shared/model/batch-service-type.model';

type EntityResponseType = HttpResponse<IBatchServiceType>;
type EntityArrayResponseType = HttpResponse<IBatchServiceType[]>;

@Injectable({ providedIn: 'root' })
export class BatchServiceTypeService {
  public resourceUrl = SERVER_API_URL + 'api/batch-service-types';

  constructor(protected http: HttpClient) {}

  create(batchServiceType: IBatchServiceType): Observable<EntityResponseType> {
    return this.http.post<IBatchServiceType>(this.resourceUrl, batchServiceType, { observe: 'response' });
  }

  update(batchServiceType: IBatchServiceType): Observable<EntityResponseType> {
    return this.http.put<IBatchServiceType>(this.resourceUrl, batchServiceType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBatchServiceType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBatchServiceType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
