import { BaseEntity } from 'src/model/base-entity';

export enum CrestState {
  DECANTATION = 'Decantação',
  FINALIZED = 'Finalizado',
}

export class Crest implements BaseEntity {
  constructor(
    public id?: number,
    public combFrameQuantity?: number,
    public waxWeight?: number,
    public timeWastedCentrifuge?: number,
    public initialDateDecantation?: any,
    public finalDateDecantation?: any,
    public producedHoneyQuantity?: number,
    public state?: CrestState,
    public hiveundefined?: string,
    public hiveId?: number
  ) {}
}
