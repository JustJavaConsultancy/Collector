import dayjs from 'dayjs';
import { SubscriberTypeEnum } from 'app/shared/model/enumerations/subscriber-type-enum.model';
import { SubscriberStatusEnum } from 'app/shared/model/enumerations/subscriber-status-enum.model';

export interface ISubscriber {
  id?: string;
  lastName?: string | null;
  firstName?: string | null;
  middleName?: string | null;
  type?: SubscriberTypeEnum | null;
  dateOfBirth?: string | null;
  phoneNumber?: string | null;
  idNumber?: string | null;
  status?: SubscriberStatusEnum | null;
  address?: string | null;
  email?: string | null;
  dateRegistered?: string | null;
}

export const defaultValue: Readonly<ISubscriber> = {};
