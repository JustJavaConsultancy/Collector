import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IFacility } from 'app/shared/model/facility.model';
import { UserTypeEnum } from 'app/shared/model/enumerations/user-type-enum.model';
import { RentCycleEnum } from 'app/shared/model/enumerations/rent-cycle-enum.model';
import { StatusEnum } from 'app/shared/model/enumerations/status-enum.model';
import { getEntity, updateEntity, createEntity, reset } from './facility.reducer';

export const FacilityUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const facilityEntity = useAppSelector(state => state.collector.facility.entity);
  const loading = useAppSelector(state => state.collector.facility.loading);
  const updating = useAppSelector(state => state.collector.facility.updating);
  const updateSuccess = useAppSelector(state => state.collector.facility.updateSuccess);
  const userTypeEnumValues = Object.keys(UserTypeEnum);
  const rentCycleEnumValues = Object.keys(RentCycleEnum);
  const statusEnumValues = Object.keys(StatusEnum);
  const handleClose = () => {
    props.history.push('/facility');
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
      ...facilityEntity,
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
          userType: 'SingleUser',
          rentCycle: 'Daily',
          status: 'Vacant',
          ...facilityEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="collectorApp.facility.home.createOrEditLabel" data-cy="FacilityCreateUpdateHeading">
            <Translate contentKey="collectorApp.facility.home.createOrEditLabel">Create or edit a Facility</Translate>
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
                  id="facility-id"
                  label={translate('collectorApp.facility.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('collectorApp.facility.description')}
                id="facility-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('collectorApp.facility.userType')}
                id="facility-userType"
                name="userType"
                data-cy="userType"
                type="select"
              >
                {userTypeEnumValues.map(userTypeEnum => (
                  <option value={userTypeEnum} key={userTypeEnum}>
                    {translate('collectorApp.UserTypeEnum.' + userTypeEnum)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('collectorApp.facility.rentCycle')}
                id="facility-rentCycle"
                name="rentCycle"
                data-cy="rentCycle"
                type="select"
              >
                {rentCycleEnumValues.map(rentCycleEnum => (
                  <option value={rentCycleEnum} key={rentCycleEnum}>
                    {translate('collectorApp.RentCycleEnum.' + rentCycleEnum)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('collectorApp.facility.location')}
                id="facility-location"
                name="location"
                data-cy="location"
                type="text"
              />
              <ValidatedField
                label={translate('collectorApp.facility.refNumber')}
                id="facility-refNumber"
                name="refNumber"
                data-cy="refNumber"
                type="text"
              />
              <ValidatedField label={translate('collectorApp.facility.size')} id="facility-size" name="size" data-cy="size" type="text" />
              <ValidatedField
                label={translate('collectorApp.facility.dateRegistered')}
                id="facility-dateRegistered"
                name="dateRegistered"
                data-cy="dateRegistered"
                type="date"
              />
              <ValidatedField
                label={translate('collectorApp.facility.status')}
                id="facility-status"
                name="status"
                data-cy="status"
                type="select"
              >
                {statusEnumValues.map(statusEnum => (
                  <option value={statusEnum} key={statusEnum}>
                    {translate('collectorApp.StatusEnum.' + statusEnum)}
                  </option>
                ))}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/facility" replace color="info">
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

export default FacilityUpdate;
