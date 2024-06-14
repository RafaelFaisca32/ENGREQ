import { BaseEntity } from 'src/model/base-entity';
import { Hive } from '../hive/hive.model';
import { TranshumanceRequest } from '../transhumance-request/transhumance-request.model';

export const enum ApiaryState {
  'NOT_APPROVED',
  'APPROVED',
  'PENDING',
  'ANNULLED',
}

export class Apiary implements BaseEntity {
  constructor(
    public id?: number,
    public name?: string,
    public state?: ApiaryState,
    public coordX?: string,
    public coordY?: string,
    public coordZ?: string,
    public number?: number,
    public intensive?: boolean,
    public transhumance?: boolean,
    public beekeeperundefined?: string,
    public beekeeperId?: number,
    public apiaryZoneundefined?: string,
    public apiaryZoneId?: number,
    public hives?: Hive[],
    public transhumanceRequests?: TranshumanceRequest[]
  ) {
    this.intensive = false;
    this.transhumance = false;
  }
}
