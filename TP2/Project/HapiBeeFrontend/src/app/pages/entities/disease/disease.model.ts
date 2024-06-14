import { BaseEntity } from 'src/model/base-entity';
import { Inspection } from '../inspection/inspection.model';

export class Disease implements BaseEntity {
  constructor(public id?: number, public name?: string, public description?: string, public inspections?: Inspection[]) {}
}
