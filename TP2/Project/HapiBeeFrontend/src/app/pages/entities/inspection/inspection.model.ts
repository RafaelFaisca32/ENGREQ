import { BaseEntity } from 'src/model/base-entity';
import { Disease } from '../disease/disease.model';

export class Inspection implements BaseEntity {
  constructor(
    public id?: number,
    public date?: any,
    public note?: string,
    public hiveundefined?: string,
    public hiveId?: number,
    public diseases?: Disease[]
  ) {}
}
