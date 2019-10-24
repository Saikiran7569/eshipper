export interface IPalletType {
  id?: number;
}

export class PalletType implements IPalletType {
  constructor(public id?: number) {}
}
