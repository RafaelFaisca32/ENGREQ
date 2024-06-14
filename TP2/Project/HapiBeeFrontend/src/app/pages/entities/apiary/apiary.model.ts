import { BaseEntity } from 'src/model/base-entity';
import { Hive } from '../hive/hive.model';
import { TranshumanceRequest } from '../transhumance-request/transhumance-request.model';

export const enum ApiaryState {
  'NOT_APPROVED',
  'APPROVED',
  'PENDING',
}

export class Apiary implements BaseEntity {
  constructor(
    public id?: number,
    public name?: string,
    public state?: ApiaryState,
    public number?: number,
    public intensive?: boolean,
    public transhumance?: boolean,
    public zoneundefined?: string,
    public zoneId?: number,
    public beekeeperundefined?: string,
    public beekeeperId?: number,
    public hives?: Hive[],
    public transhumanceRequests?: TranshumanceRequest[]
  ) {
    this.intensive = false;
    this.transhumance = false;
  }
}
