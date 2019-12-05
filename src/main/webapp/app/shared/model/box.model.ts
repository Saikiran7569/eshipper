import { Moment } from 'moment';

export interface IBox {
  id?: number;
  name?: string;
  description?: string;
  maxSupportWeight?: number;
  length?: number;
  width?: number;
  height?: number;
  weight?: number;
  dateCreated?: Moment;
  dateUpdated?: Moment;
  createdByUserId?: number;
  metricId?: number;
  boxPackageTypeId?: number;
  companyId?: number;
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
    public dateCreated?: Moment,
    public dateUpdated?: Moment,
    public createdByUserId?: number,
    public metricId?: number,
    public boxPackageTypeId?: number,
    public companyId?: number
  ) {}
}
