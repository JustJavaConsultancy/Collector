import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDateInterval } from 'app/shared/model/date-interval.model';
import { DateIntervalMeasureEnum } from 'app/shared/model/enumerations/date-interval-measure-enum.model';
import { getEntity, updateEntity, createEntity, reset } from './date-interval.reducer';

export const DateIntervalUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const dateIntervalEntity = useAppSelector(state => state.collector.dateInterval.entity);
  const loading = useAppSelector(state => state.collector.dateInterval.loading);
  const updating = useAppSelector(state => state.collector.dateInterval.updating);
  const updateSuccess = useAppSelector(state => state.collector.dateInterval.updateSuccess);
  const dateIntervalMeasureEnumValues = Object.keys(DateIntervalMeasureEnum);
  const handleClose = () => {
    props.history.push('/date-interval');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...dateIntervalEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          measure: 'Day',
          ...dateIntervalEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="collectorApp.dateInterval.home.createOrEditLabel" data-cy="DateIntervalCreateUpdateHeading">
            <Translate contentKey="collectorApp.dateInterval.home.createOrEditLabel">Create or edit a DateInterval</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="date-interval-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('collectorApp.dateInterval.interval')}
                id="date-interval-interval"
                name="interval"
                data-cy="interval"
                type="text"
              />
              <ValidatedField
                label={translate('collectorApp.dateInterval.measure')}
                id="date-interval-measure"
                name="measure"
                data-cy="measure"
                type="select"
              >
                {dateIntervalMeasureEnumValues.map(dateIntervalMeasureEnum => (
                  <option value={dateIntervalMeasureEnum} key={dateIntervalMeasureEnum}>
                    {translate('collectorApp.DateIntervalMeasureEnum.' + dateIntervalMeasureEnum)}
                  </option>
                ))}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/date-interval" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default DateIntervalUpdate;
