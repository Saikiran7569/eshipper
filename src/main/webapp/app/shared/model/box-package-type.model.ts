export interface IBoxPackageType {
  id?: number;
  name?: string;
}

export class BoxPackageType implements IBoxPackageType {
  constructor(public id?: number, public name?: string) {}
}
