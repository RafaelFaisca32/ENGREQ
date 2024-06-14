import { BaseEntity } from 'src/model/base-entity';
import { TranshumanceRequest } from '../transhumance-request/transhumance-request.model';
import { Apiary } from '../apiary/apiary.model';

export const enum BeekeeperState {
  'REGISTERED',
  'ANNULLED',
}

export class Beekeeper implements BaseEntity {
  constructor(
    public id?: number,
    public beekeeperCompleteName?: string,
    public beekeeperNumber?: number,
    public entityZoneResidence?: number,
    public nif?: number,
    public address?: string,
    public postalCode?: string,
    public phoneNumber?: number,
    public faxNumber?: number,
    public state?: BeekeeperState,
    public userId?: number,
    public transhumanceRequests?: TranshumanceRequest[],
    public apiaries?: Apiary[]
  ) {}
}
