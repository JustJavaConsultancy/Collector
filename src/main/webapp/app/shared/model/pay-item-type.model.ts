import { TypeEnum } from 'app/shared/model/enumerations/type-enum.model';
import { RateTypeEnum } from 'app/shared/model/enumerations/rate-type-enum.model';

export interface IPayItemType {
  description?: string | null;
  rate?: number | null;
  type?: TypeEnum | null;
  rateType?: RateTypeEnum | null;
  rentComponent?: boolean | null;
}

export const defaultValue: Readonly<IPayItemType> = {
  rentComponent: false,
};
