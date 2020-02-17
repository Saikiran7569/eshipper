import { ISupply } from 'app/shared/model/supply.model';

export interface ICarrier {
  id?: number;
  supplies?: ISupply[];
}

export class Carrier implements ICarrier {
  constructor(public id?: number, public supplies?: ISupply[]) {}
}
