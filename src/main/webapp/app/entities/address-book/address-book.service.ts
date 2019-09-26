import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAddressBook } from 'app/shared/model/address-book.model';

type EntityResponseType = HttpResponse<IAddressBook>;
type EntityArrayResponseType = HttpResponse<IAddressBook[]>;

@Injectable({ providedIn: 'root' })
export class AddressBookService {
  public resourceUrl = SERVER_API_URL + 'api/address-books';

  constructor(protected http: HttpClient) {}

  create(addressBook: IAddressBook): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(addressBook);
    return this.http
      .post<IAddressBook>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(addressBook: IAddressBook): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(addressBook);
    return this.http
      .put<IAddressBook>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAddressBook>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAddressBook[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(addressBook: IAddressBook): IAddressBook {
    const copy: IAddressBook = Object.assign({}, addressBook, {
      dateCreated:
        addressBook.dateCreated != null && addressBook.dateCreated.isValid() ? addressBook.dateCreated.format(DATE_FORMAT) : null,
      dateUpdated: addressBook.dateUpdated != null && addressBook.dateUpdated.isValid() ? addressBook.dateUpdated.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateCreated = res.body.dateCreated != null ? moment(res.body.dateCreated) : null;
      res.body.dateUpdated = res.body.dateUpdated != null ? moment(res.body.dateUpdated) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((addressBook: IAddressBook) => {
        addressBook.dateCreated = addressBook.dateCreated != null ? moment(addressBook.dateCreated) : null;
        addressBook.dateUpdated = addressBook.dateUpdated != null ? moment(addressBook.dateUpdated) : null;
      });
    }
    return res;
  }
}
