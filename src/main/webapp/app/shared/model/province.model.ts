import { IAddressBook } from 'app/shared/model/address-book.model';

export interface IProvince {
  id?: number;
  name?: string;
  fullName?: string;
  addressBooks?: IAddressBook[];
}

export class Province implements IProvince {
  constructor(public id?: number, public name?: string, public fullName?: string, public addressBooks?: IAddressBook[]) {}
}
