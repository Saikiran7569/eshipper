export interface IShippingOrder {
  id?: number;
  commodityTypeId?: number;
}

export class ShippingOrder implements IShippingOrder {
  constructor(public id?: number, public commodityTypeId?: number) {}
}
