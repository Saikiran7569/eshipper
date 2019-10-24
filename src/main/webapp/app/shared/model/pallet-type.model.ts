export interface IPalletType {
  id?: number;
  name?: string;
}

export class PalletType implements IPalletType {
  constructor(public id?: number, public name?: string) {}
}
