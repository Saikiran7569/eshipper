export interface ICompany {
  id?: number;
  industryId?: number;
  monthlyshipmentsId?: number;
}

export class Company implements ICompany {
  constructor(public id?: number, public industryId?: number, public monthlyshipmentsId?: number) {}
}
