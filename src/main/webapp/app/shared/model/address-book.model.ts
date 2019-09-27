import { Moment } from 'moment';
import { ICountry } from 'app/shared/model/country.model';
import { IProvince } from 'app/shared/model/province.model';
import { ICity } from 'app/shared/model/city.model';
import { ICustomer } from 'app/shared/model/customer.model';

export interface IAddressBook {
  id?: number;
  companyName?: string;
  address1?: string;
  address2?: string;
  postalCode?: string;
  contactName?: string;
  phoneNo?: string;
  contactEmail?: string;
  province?: string;
  notify?: boolean;
  residential?: boolean;
  createdByUser?: string;
  instruction?: string;
  dateCreated?: Moment;
  dateUpdated?: Moment;
  country?: ICountry;
  province?: IProvince;
  city?: ICity;
  customer?: ICustomer;
}

export class AddressBook implements IAddressBook {
  constructor(
    public id?: number,
    public companyName?: string,
    public address1?: string,
    public address2?: string,
    public postalCode?: string,
    public contactName?: string,
    public phoneNo?: string,
    public contactEmail?: string,
    public province?: string,
    public notify?: boolean,
    public residential?: boolean,
    public createdByUser?: string,
    public instruction?: string,
    public dateCreated?: Moment,
    public dateUpdated?: Moment,
    public country?: ICountry,
    public province?: IProvince,
    public city?: ICity,
    public customer?: ICustomer
  ) {
    this.notify = this.notify || false;
    this.residential = this.residential || false;
  }
}
