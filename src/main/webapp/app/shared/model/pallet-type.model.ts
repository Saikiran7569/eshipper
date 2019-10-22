import { IBox } from 'app/shared/model/box.model';

export interface IPalletType {
  id?: number;
  name?: string;
  boxes?: IBox[];
}

export class PalletType implements IPalletType {
  constructor(public id?: number, public name?: string, public boxes?: IBox[]) {}
}
