export interface IShipmentPackage {
  id?: number;
  description?: string;
  length?: number;
  width?: number;
  height?: number;
  weight?: number;
  position?: number;
  trackingNumber?: string;
  cubedWeight?: number;
  codValue?: number;
  insuranceAmount?: number;
  freightClass?: string;
  nmfcCode?: string;
  weightOz?: number;
  itemValue?: number;
  harmonizedCode?: string;
  typeId?: number;
}

export class ShipmentPackage implements IShipmentPackage {
  constructor(
    public id?: number,
    public description?: string,
    public length?: number,
    public width?: number,
    public height?: number,
    public weight?: number,
    public position?: number,
    public trackingNumber?: string,
    public cubedWeight?: number,
    public codValue?: number,
    public insuranceAmount?: number,
    public freightClass?: string,
    public nmfcCode?: string,
    public weightOz?: number,
    public itemValue?: number,
    public harmonizedCode?: string,
    public typeId?: number
  ) {}
}
