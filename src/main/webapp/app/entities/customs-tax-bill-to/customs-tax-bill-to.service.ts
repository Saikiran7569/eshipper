import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustomsTaxBillTo } from 'app/shared/model/customs-tax-bill-to.model';

type EntityResponseType = HttpResponse<ICustomsTaxBillTo>;
type EntityArrayResponseType = HttpResponse<ICustomsTaxBillTo[]>;

@Injectable({ providedIn: 'root' })
export class CustomsTaxBillToService {
  public resourceUrl = SERVER_API_URL + 'api/customs-tax-bill-tos';

  constructor(protected http: HttpClient) {}

  create(customsTaxBillTo: ICustomsTaxBillTo): Observable<EntityResponseType> {
    return this.http.post<ICustomsTaxBillTo>(this.resourceUrl, customsTaxBillTo, { observe: 'response' });
  }

  update(customsTaxBillTo: ICustomsTaxBillTo): Observable<EntityResponseType> {
    return this.http.put<ICustomsTaxBillTo>(this.resourceUrl, customsTaxBillTo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICustomsTaxBillTo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICustomsTaxBillTo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
