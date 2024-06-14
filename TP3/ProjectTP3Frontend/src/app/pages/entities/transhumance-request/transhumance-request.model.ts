import { BaseEntity } from 'src/model/base-entity';
import { Hive } from '../hive/hive.model';

export const enum TranshumanceRequestState {
  'NOT_APPROVED',
  'APPROVED',
  'PENDING',
  'ANNULLED',
}

export class TranshumanceRequest implements BaseEntity {
  constructor(
    public id?: number,
    public requestDate?: any,
    public state?: TranshumanceRequestState,
    public beekeeperundefined?: string,
    public beekeeperId?: number,
    public apiaryundefined?: string,
    public destinationApiaryId? : number,
    public apiaryId?: number,
    public hives?: Hive[]
  ) {}
}
