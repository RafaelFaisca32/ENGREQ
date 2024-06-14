import { BaseEntity } from 'src/model/base-entity';
import { Apiary } from '../apiary/apiary.model';

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
    public userId?: number,
    public apiaries?: Apiary[]
  ) {}
}
