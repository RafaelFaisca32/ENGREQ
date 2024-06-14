import { BaseEntity } from 'src/model/base-entity';

export class ApiaryInformation implements BaseEntity {
  constructor(
    public id?: number,
    public zoneNumber?: number,
    public zoneName?: string,
    public numberHives?: number,
    public intensive?: boolean,
    public transhumance?: boolean,
    public coordX?: string,
    public coordY?: string,
    public coordZ?: string,
    public numberFrames?: number,
    public annualInventoryDeclarationundefined?: string,
    public annualInventoryDeclarationId?: number
  ) {
    this.intensive = false;
    this.transhumance = false;
  }
}
