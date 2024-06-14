import { BaseEntity } from 'src/model/base-entity';

export class Frame implements BaseEntity {
  constructor(public id?: number, public code?: string, public hiveundefined?: string, public hiveId?: number) {}
}
