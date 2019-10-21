import { Moment } from 'moment';

export interface IAddressBook {
  id?: number;
  companyName?: string;
  address1?: string;
  address2?: string;
  postalCode?: string;
  contactName?: string;
  phoneNo?: string;
  contactEmail?: string;
  notify?: boolean;
  defaultFrom?: boolean;
  residential?: boolean;
  instruction?: string;
  dateCreated?: Moment;
  dateUpdated?: Moment;
  countryId?: number;
  provinceId?: number;
  cityId?: number;
  createdById?: number;
  companyId?: number;
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
    public notify?: boolean,
    public defaultFrom?: boolean,
    public residential?: boolean,
    public instruction?: string,
    public dateCreated?: Moment,
    public dateUpdated?: Moment,
    public countryId?: number,
    public provinceId?: number,
    public cityId?: number,
    public createdById?: number,
    public companyId?: number
  ) {
    this.notify = this.notify || false;
    this.defaultFrom = this.defaultFrom || false;
    this.residential = this.residential || false;
  }
}
