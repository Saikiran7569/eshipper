export interface IBatchServiceType {
  id?: number;
  serviceName?: string;
  value?: string;
}

export class BatchServiceType implements IBatchServiceType {
  constructor(public id?: number, public serviceName?: string, public value?: string) {}
}
