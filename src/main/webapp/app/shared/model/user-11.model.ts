import { ISuppliesOrders } from 'app/shared/model/supplies-orders.model';

export interface IUser11 {
  id?: number;
  suppliesOrders?: ISuppliesOrders[];
}

export class User11 implements IUser11 {
  constructor(public id?: number, public suppliesOrders?: ISuppliesOrders[]) {}
}
