export interface ISupply {
  id?: number;
  itemName?: string;
  logoPath?: string;
  carrierId?: number;
}

export class Supply implements ISupply {
  constructor(public id?: number, public itemName?: string, public logoPath?: string, public carrierId?: number) {}
}
