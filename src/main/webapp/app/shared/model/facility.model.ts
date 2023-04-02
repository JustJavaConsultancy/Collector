import dayjs from 'dayjs';
import { UserTypeEnum } from 'app/shared/model/enumerations/user-type-enum.model';
import { RentCycleEnum } from 'app/shared/model/enumerations/rent-cycle-enum.model';
import { StatusEnum } from 'app/shared/model/enumerations/status-enum.model';

export interface IFacility {
  id?: string;
  description?: string | null;
  userType?: UserTypeEnum | null;
  rentCycle?: RentCycleEnum | null;
  location?: string | null;
  refNumber?: string | null;
  size?: number | null;
  dateRegistered?: string | null;
  status?: StatusEnum | null;
}

export const defaultValue: Readonly<IFacility> = {};
