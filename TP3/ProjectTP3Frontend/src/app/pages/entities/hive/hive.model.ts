import { BaseEntity } from 'src/model/base-entity';
import { Frame } from '../frame/frame.model';
import { Crest } from '../crest/crest.model';
import { Unfolding } from '../unfolding/unfolding.model';
import { Inspection } from '../inspection/inspection.model';
import { TranshumanceRequest } from '../transhumance-request/transhumance-request.model';
import { Apiary } from "../apiary";

export class Hive implements BaseEntity {
  constructor(
    public id?: number,
    public code?: string,
    public apiaryundefined?: string,
    public apiaryId?: number,
    public apiary?: Apiary,
    public frames?: Frame[],
    public unfoldingCreatedHiveId?: number,
    public crests?: Crest[],
    public unfoldings?: Unfolding[],
    public inspections?: Inspection[],
    public transhumanceRequests?: TranshumanceRequest[]
  ) {}
}
