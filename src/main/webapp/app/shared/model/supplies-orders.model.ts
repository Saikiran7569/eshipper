import { Moment } from 'moment';

export interface ISuppliesOrders {
  id?: number;
  itemName?: string;
  quantity?: number;
  createdDate?: Moment;
  createdByUserId?: number;
}

export class SuppliesOrders implements ISuppliesOrders {
  constructor(
    public id?: number,
    public itemName?: string,
    public quantity?: number,
    public createdDate?: Moment,
    public createdByUserId?: number
  ) {}
}
