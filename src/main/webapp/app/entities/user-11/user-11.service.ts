import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUser11 } from 'app/shared/model/user-11.model';

type EntityResponseType = HttpResponse<IUser11>;
type EntityArrayResponseType = HttpResponse<IUser11[]>;

@Injectable({ providedIn: 'root' })
export class User11Service {
  public resourceUrl = SERVER_API_URL + 'api/user-11-s';

  constructor(protected http: HttpClient) {}

  create(user11: IUser11): Observable<EntityResponseType> {
    return this.http.post<IUser11>(this.resourceUrl, user11, { observe: 'response' });
  }

  update(user11: IUser11): Observable<EntityResponseType> {
    return this.http.put<IUser11>(this.resourceUrl, user11, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUser11>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUser11[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
