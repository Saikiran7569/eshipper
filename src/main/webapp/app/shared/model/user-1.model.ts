import { IAddressBook } from 'app/shared/model/address-book.model';

export interface IUser1 {
  id?: number;
  name?: string;
  createdByUserIds?: IAddressBook[];
}

export class User1 implements IUser1 {
  constructor(public id?: number, public name?: string, public createdByUserIds?: IAddressBook[]) {}
}