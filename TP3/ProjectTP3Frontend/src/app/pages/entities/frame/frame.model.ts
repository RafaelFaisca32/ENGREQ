import { BaseEntity } from 'src/model/base-entity';
import { Hive } from '../hive/hive.model';
import { Crest } from '../crest/crest.model';
import { Unfolding } from '../unfolding/unfolding.model';

export class Frame implements BaseEntity {
  constructor(
    public id?: number,
    public code?: string,
    public hives?: Hive[],
    public crests?: Crest[],
    public unfoldingRemovedFramesOldHives?: Unfolding[],
    public unfoldingInsertedFramesOldHives?: Unfolding[],
    public unfoldingInsertedFramesNewHives?: Unfolding[]
  ) {}
}
