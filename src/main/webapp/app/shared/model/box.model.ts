export interface IBox {
  id?: number;
  name?: string;
  description?: string;
  maxSupportWeight?: number;
  length?: number;
  width?: number;
  height?: number;
  weight?: number;
  createdByUserId?: number;
  metricId?: number;
  woPackageTypeId?: number;
}

export class Box implements IBox {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public maxSupportWeight?: number,
    public length?: number,
    public width?: number,
    public height?: number,
    public weight?: number,
    public createdByUserId?: number,
    public metricId?: number,
    public woPackageTypeId?: number
  ) {}
}
