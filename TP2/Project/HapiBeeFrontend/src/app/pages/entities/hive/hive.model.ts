import { BaseEntity } from 'src/model/base-entity';
import { Crest } from '../crest/crest.model';
import { Unfolding } from '../unfolding/unfolding.model';
import { Frame } from '../frame/frame.model';
import { Inspection } from '../inspection/inspection.model';
import { TranshumanceRequest } from '../transhumance-request/transhumance-request.model';
import {Apiary} from "../apiary";

export class Hive implements BaseEntity {
  constructor(
    public id?: number,
    public code?: string,
    public apiaryundefined?: string,
    public apiaryId?: number,
    public apiary?: Apiary,
    public crests?: Crest[],
    public unfoldings?: Unfolding[],
    public frames?: Frame[],
    public inspections?: Inspection[],
    public transhumanceRequests?: TranshumanceRequest[]
  ) {}
}
