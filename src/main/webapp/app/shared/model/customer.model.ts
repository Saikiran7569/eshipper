import { Moment } from 'moment';
import { IAddressBook } from 'app/shared/model/address-book.model';

export interface ICustomer {
  id?: number;
  accountNumber?: string;
  name?: string;
  address1?: string;
  address2?: string;
  postalCode?: string;
  phone?: string;
  email?: string;
  timeZone?: string;
  costAccount?: number;
  dateCreated?: Moment;
  creator?: number;
  contact?: string;
  isShopifyEnable?: boolean;
  defaultSignatureOption?: number;
  addressBooks?: IAddressBook[];
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public accountNumber?: string,
    public name?: string,
    public address1?: string,
    public address2?: string,
    public postalCode?: string,
    public phone?: string,
    public email?: string,
    public timeZone?: string,
    public costAccount?: number,
    public dateCreated?: Moment,
    public creator?: number,
    public contact?: string,
    public isShopifyEnable?: boolean,
    public defaultSignatureOption?: number,
    public addressBooks?: IAddressBook[]
  ) {
    this.isShopifyEnable = this.isShopifyEnable || false;
  }
}
