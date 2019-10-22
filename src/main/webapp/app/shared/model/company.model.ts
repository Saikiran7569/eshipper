import { IBox } from 'app/shared/model/box.model';

export interface ICompany {
  id?: number;
  boxes?: IBox[];
}

export class Company implements ICompany {
  constructor(public id?: number, public boxes?: IBox[]) {}
}
