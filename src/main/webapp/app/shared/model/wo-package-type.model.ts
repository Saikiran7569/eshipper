import { IBox } from 'app/shared/model/box.model';

export interface IWoPackageType {
  id?: number;
  boxes?: IBox[];
}

export class WoPackageType implements IWoPackageType {
  constructor(public id?: number, public boxes?: IBox[]) {}
}
