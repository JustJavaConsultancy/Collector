import subscriber from 'app/entities/subscriber/subscriber.reducer';
import facility from 'app/entities/facility/facility.reducer';
import dateInterval from 'app/entities/date-interval/date-interval.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  subscriber,
  facility,
  dateInterval,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
