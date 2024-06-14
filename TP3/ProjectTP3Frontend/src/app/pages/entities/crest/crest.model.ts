import { BaseEntity } from 'src/model/base-entity';
import { Frame } from '../frame/frame.model';

export enum CrestState {
  DECANTATION = 'Decantação',
  FINALIZED = 'Finalizado',
  ANNULLED = 'Anulado',
}

export class Crest implements BaseEntity {
  constructor(
    public id?: number,
    public waxWeight?: number,
    public timeWastedCentrifuge?: number,
    public initialDateDecantation?: any,
    public finalDateDecantation?: any,
    public producedHoneyQuantity?: number,
    public state?: CrestState,
    public hiveundefined?: string,
    public hiveId?: number,
    public frames?: Frame[]
  ) {}
}
