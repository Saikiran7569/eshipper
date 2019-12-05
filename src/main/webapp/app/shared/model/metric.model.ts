export interface IMetric {
  id?: number;
}

export class Metric implements IMetric {
  constructor(public id?: number) {}
}
