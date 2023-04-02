import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ISubscriber } from 'app/shared/model/subscriber.model';
import { SubscriberTypeEnum } from 'app/shared/model/enumerations/subscriber-type-enum.model';
import { SubscriberStatusEnum } from 'app/shared/model/enumerations/subscriber-status-enum.model';
import { getEntity, updateEntity, createEntity, reset } from './subscriber.reducer';

export const SubscriberUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const subscriberEntity = useAppSelector(state => state.collector.subscriber.entity);
  const loading = useAppSelector(state => state.collector.subscriber.loading);
  const updating = useAppSelector(state => state.collector.subscriber.updating);
  const updateSuccess = useAppSelector(state => state.collector.subscriber.updateSuccess);
  const subscriberTypeEnumValues = Object.keys(SubscriberTypeEnum);
  const subscriberStatusEnumValues = Object.keys(SubscriberStatusEnum);
  const handleClose = () => {
    props.history.push('/subscriber');
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
      ...subscriberEntity,
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
          type: 'Individual',
          status: 'Active',
          ...subscriberEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="collectorApp.subscriber.home.createOrEditLabel" data-cy="SubscriberCreateUpdateHeading">
            <Translate contentKey="collectorApp.subscriber.home.createOrEditLabel">Create or edit a Subscriber</Translate>
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
                  id="subscriber-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('collectorApp.subscriber.lastName')}
                id="subscriber-lastName"
                name="lastName"
                data-cy="lastName"
                type="text"
              />
              <ValidatedField
                label={translate('collectorApp.subscriber.firstName')}
                id="subscriber-firstName"
                name="firstName"
                data-cy="firstName"
                type="text"
              />
              <ValidatedField
                label={translate('collectorApp.subscriber.middleName')}
                id="subscriber-middleName"
                name="middleName"
                data-cy="middleName"
                type="text"
              />
              <UncontrolledTooltip target="middleNameLabel">
                <Translate contentKey="collectorApp.subscriber.help.middleName" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('collectorApp.subscriber.type')}
                id="subscriber-type"
                name="type"
                data-cy="type"
                type="select"
              >
                {subscriberTypeEnumValues.map(subscriberTypeEnum => (
                  <option value={subscriberTypeEnum} key={subscriberTypeEnum}>
                    {translate('collectorApp.SubscriberTypeEnum.' + subscriberTypeEnum)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('collectorApp.subscriber.dateOfBirth')}
                id="subscriber-dateOfBirth"
                name="dateOfBirth"
                data-cy="dateOfBirth"
                type="date"
              />
              <ValidatedField
                label={translate('collectorApp.subscriber.phoneNumber')}
                id="subscriber-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
              />
              <ValidatedField
                label={translate('collectorApp.subscriber.idNumber')}
                id="subscriber-idNumber"
                name="idNumber"
                data-cy="idNumber"
                type="text"
              />
              <ValidatedField
                label={translate('collectorApp.subscriber.status')}
                id="subscriber-status"
                name="status"
                data-cy="status"
                type="select"
              >
                {subscriberStatusEnumValues.map(subscriberStatusEnum => (
                  <option value={subscriberStatusEnum} key={subscriberStatusEnum}>
                    {translate('collectorApp.SubscriberStatusEnum.' + subscriberStatusEnum)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('collectorApp.subscriber.address')}
                id="subscriber-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField
                label={translate('collectorApp.subscriber.email')}
                id="subscriber-email"
                name="email"
                data-cy="email"
                type="text"
              />
              <ValidatedField
                label={translate('collectorApp.subscriber.dateRegistered')}
                id="subscriber-dateRegistered"
                name="dateRegistered"
                data-cy="dateRegistered"
                type="date"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/subscriber" replace color="info">
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

export default SubscriberUpdate;
