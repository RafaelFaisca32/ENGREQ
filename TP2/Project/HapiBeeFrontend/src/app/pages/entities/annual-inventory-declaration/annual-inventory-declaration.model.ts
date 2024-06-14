import { BaseEntity } from 'src/model/base-entity';
import { ApiaryInformation } from '../apiary-information/apiary-information.model';

export const enum AnnualInventoyDeclarationState {
  // eslint-disable-next-line @typescript-eslint/naming-convention
  'APPROVED',
  'DECLINED',
  'PENDING',
}

export class AnnualInventoryDeclaration implements BaseEntity {
  constructor(
    public id?: number,
    public total?: number,
    public beekeeperFaxNumber?: number,
    public beekeeperCompleteName?: string,
    public beekeeperNif?: number,
    public date?: any,
    public beekeeperAddress?: string,
    public beekeeperPostalCode?: string,
    public beekeeperPhoneNumber?: number,
    public beekeeperEntityZoneResidence?: string,
    public beekeeperNumber?: number,
    public annualInventoryDeclarationState?: AnnualInventoyDeclarationState,
    public revisionDate?: any,
    public revisionLocation?: string,
    public revisorSignature?: string,
    public revisorName?: string,
    public apiaryInformations?: ApiaryInformation[]
  ) {}
}
