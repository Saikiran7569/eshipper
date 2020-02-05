export interface ICustomsTaxBillTo {
  id?: number;
  billTo?: string;
}

export class CustomsTaxBillTo implements ICustomsTaxBillTo {
  constructor(public id?: number, public billTo?: string) {}
}
