import { BaseEntity } from 'src/model/base-entity';
import { Disease } from '../disease/disease.model';
import {Hive} from "../hive";

export const enum InspectionType {
  'EXTERNAL',
  'INTERNAL',
}

export const enum InspectionState {
  'REGISTERED',
  'ANNULLED',
}

export class Inspection implements BaseEntity {
  constructor(
    public id?: number,
    public date?: any,
    public note?: string,
    public type?: InspectionType,
    public state?: InspectionState,
    public hiveundefined?: string,
    public hiveId?: number,
    public hive?: Hive,
    public diseases?: Disease[]
  ) {}
}
