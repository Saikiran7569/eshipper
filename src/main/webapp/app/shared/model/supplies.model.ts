export interface ISupplies {
  id?: number;
  item?: string;
  name?: string;
  suppliesId?: number;
}

export class Supplies implements ISupplies {
  constructor(public id?: number, public item?: string, public name?: string, public suppliesId?: number) {}
}
