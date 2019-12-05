import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBoxPackageType } from 'app/shared/model/box-package-type.model';

type EntityResponseType = HttpResponse<IBoxPackageType>;
type EntityArrayResponseType = HttpResponse<IBoxPackageType[]>;

@Injectable({ providedIn: 'root' })
export class BoxPackageTypeService {
  public resourceUrl = SERVER_API_URL + 'api/box-package-types';

  constructor(protected http: HttpClient) {}

  create(boxPackageType: IBoxPackageType): Observable<EntityResponseType> {
    return this.http.post<IBoxPackageType>(this.resourceUrl, boxPackageType, { observe: 'response' });
  }

  update(boxPackageType: IBoxPackageType): Observable<EntityResponseType> {
    return this.http.put<IBoxPackageType>(this.resourceUrl, boxPackageType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBoxPackageType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBoxPackageType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
