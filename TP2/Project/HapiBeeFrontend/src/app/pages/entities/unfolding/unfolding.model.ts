import { BaseEntity } from 'src/model/base-entity';
import {Hive} from "../hive";

export class Unfolding implements BaseEntity {

  constructor(public id?: number, public observations?: string, public date?: any, public hiveundefined?: string, public hiveId?: number, public hive?: Hive) {}
}
