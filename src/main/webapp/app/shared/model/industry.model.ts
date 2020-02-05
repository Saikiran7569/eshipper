export interface IIndustry {
  id?: number;
  name?: string;
}

export class Industry implements IIndustry {
  constructor(public id?: number, public name?: string) {}
}
