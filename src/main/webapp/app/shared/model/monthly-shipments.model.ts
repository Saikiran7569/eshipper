export interface IMonthlyShipments {
  id?: number;
  range?: string;
}

export class MonthlyShipments implements IMonthlyShipments {
  constructor(public id?: number, public range?: string) {}
}
