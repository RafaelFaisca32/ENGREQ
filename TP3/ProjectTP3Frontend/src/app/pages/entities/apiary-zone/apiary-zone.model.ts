import { BaseEntity } from 'src/model/base-entity';
import { Apiary } from '../apiary/apiary.model';

export const enum ApiaryZoneState {
  'REGISTERED',
  'ANNULLED',
}

export class ApiaryZone implements BaseEntity {
  constructor(
    public id?: number,
    public name?: string,
    public controlledZone?: boolean,
    public state?: ApiaryZoneState,
    public apiaries?: Apiary[]
  ) {
    this.controlledZone = false;
  }
}
