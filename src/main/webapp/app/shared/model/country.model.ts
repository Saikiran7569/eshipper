import { IAddressBook } from 'app/shared/model/address-book.model';

export interface ICountry {
  id?: number;
  name?: string;
  fullName?: string;
  isRestricted?: boolean;
  addressBooks?: IAddressBook[];
}

export class Country implements ICountry {
  constructor(
    public id?: number,
    public name?: string,
    public fullName?: string,
    public isRestricted?: boolean,
    public addressBooks?: IAddressBook[]
  ) {
    this.isRestricted = this.isRestricted || false;
  }
}
