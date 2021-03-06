import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IIndustry } from 'app/shared/model/industry.model';

type EntityResponseType = HttpResponse<IIndustry>;
type EntityArrayResponseType = HttpResponse<IIndustry[]>;

@Injectable({ providedIn: 'root' })
export class IndustryService {
  public resourceUrl = SERVER_API_URL + 'api/industries';

  constructor(protected http: HttpClient) {}

  create(industry: IIndustry): Observable<EntityResponseType> {
    return this.http.post<IIndustry>(this.resourceUrl, industry, { observe: 'response' });
  }

  update(industry: IIndustry): Observable<EntityResponseType> {
    return this.http.put<IIndustry>(this.resourceUrl, industry, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IIndustry>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IIndustry[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
