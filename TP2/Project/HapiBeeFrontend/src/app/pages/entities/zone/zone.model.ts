import { BaseEntity } from 'src/model/base-entity';

export class Zone implements BaseEntity {
  constructor(
    public id?: number,
    public name?: string,
    public number?: number,
    public coordX?: string,
    public coordY?: string,
    public coordZ?: string,
    public controlledZone?: boolean,
    public apiaryId?: number
  ) {
    this.controlledZone = false;
  }
}
