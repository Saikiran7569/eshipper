import { IBox } from 'app/shared/model/box.model';

export interface IMetric {
  id?: number;
  boxes?: IBox[];
}

export class Metric implements IMetric {
  constructor(public id?: number, public boxes?: IBox[]) {}
}
