import { IAddressBook } from 'app/shared/model/address-book.model';

export interface ICity {
  id?: number;
  city?: string;
  addressBooks?: IAddressBook[];
}

export class City implements ICity {
  constructor(public id?: number, public city?: string, public addressBooks?: IAddressBook[]) {}
}
