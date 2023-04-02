import { DateIntervalMeasureEnum } from 'app/shared/model/enumerations/date-interval-measure-enum.model';

export interface IDateInterval {
  id?: string;
  interval?: number | null;
  measure?: DateIntervalMeasureEnum | null;
}

export const defaultValue: Readonly<IDateInterval> = {};
