export interface ICommodityType {
  id?: number;
  name?: string;
}

export class CommodityType implements ICommodityType {
  constructor(public id?: number, public name?: string) {}
}
