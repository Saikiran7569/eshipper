import { ISupplies } from 'app/shared/model/supplies.model';

export interface ICarrier {
  id?: number;
  carriers?: ISupplies[];
}

export class Carrier implements ICarrier {
  constructor(public id?: number, public carriers?: ISupplies[]) {}
}
