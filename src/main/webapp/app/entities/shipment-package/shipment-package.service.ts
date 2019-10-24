import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IShipmentPackage } from 'app/shared/model/shipment-package.model';

type EntityResponseType = HttpResponse<IShipmentPackage>;
type EntityArrayResponseType = HttpResponse<IShipmentPackage[]>;

@Injectable({ providedIn: 'root' })
export class ShipmentPackageService {
  public resourceUrl = SERVER_API_URL + 'api/shipment-packages';

  constructor(protected http: HttpClient) {}

  create(shipmentPackage: IShipmentPackage): Observable<EntityResponseType> {
    return this.http.post<IShipmentPackage>(this.resourceUrl, shipmentPackage, { observe: 'response' });
  }

  update(shipmentPackage: IShipmentPackage): Observable<EntityResponseType> {
    return this.http.put<IShipmentPackage>(this.resourceUrl, shipmentPackage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IShipmentPackage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IShipmentPackage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
