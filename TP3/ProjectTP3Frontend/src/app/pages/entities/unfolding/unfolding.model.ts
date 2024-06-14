import { BaseEntity } from 'src/model/base-entity';
import { Frame } from '../frame/frame.model';
import {Hive} from "../hive";

export const enum UnfoldingState {
  'REGISTERED',
  'ANNULLED',
}

export class Unfolding implements BaseEntity {
  constructor(
    public id?: number,
    public observations?: string,
    public date?: any,
    public state?: UnfoldingState,
    public createdHiveundefined?: string,
    public createdHiveId?: number,
    public createdHive?: Hive,
    public hiveundefined?: string,
    public hiveId?: number,
    public hive?: Hive,
    public removedFramesOldHives?: Frame[],
    public insertedFramesOldHives?: Frame[],
    public insertedFramesNewHives?: Frame[]
  ) {}
}
