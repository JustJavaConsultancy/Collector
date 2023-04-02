import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './facility.reducer';

export const FacilityDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const facilityEntity = useAppSelector(state => state.collector.facility.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="facilityDetailsHeading">
          <Translate contentKey="collectorApp.facility.detail.title">Facility</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="collectorApp.facility.id">Id</Translate>
            </span>
          </dt>
          <dd>{facilityEntity.id}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="collectorApp.facility.description">Description</Translate>
            </span>
          </dt>
          <dd>{facilityEntity.description}</dd>
          <dt>
            <span id="userType">
              <Translate contentKey="collectorApp.facility.userType">User Type</Translate>
            </span>
          </dt>
          <dd>{facilityEntity.userType}</dd>
          <dt>
            <span id="rentCycle">
              <Translate contentKey="collectorApp.facility.rentCycle">Rent Cycle</Translate>
            </span>
          </dt>
          <dd>{facilityEntity.rentCycle}</dd>
          <dt>
            <span id="location">
              <Translate contentKey="collectorApp.facility.location">Location</Translate>
            </span>
          </dt>
          <dd>{facilityEntity.location}</dd>
          <dt>
            <span id="refNumber">
              <Translate contentKey="collectorApp.facility.refNumber">Ref Number</Translate>
            </span>
          </dt>
          <dd>{facilityEntity.refNumber}</dd>
          <dt>
            <span id="size">
              <Translate contentKey="collectorApp.facility.size">Size</Translate>
            </span>
          </dt>
          <dd>{facilityEntity.size}</dd>
          <dt>
            <span id="dateRegistered">
              <Translate contentKey="collectorApp.facility.dateRegistered">Date Registered</Translate>
            </span>
          </dt>
          <dd>
            {facilityEntity.dateRegistered ? (
              <TextFormat value={facilityEntity.dateRegistered} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="status">
              <Translate contentKey="collectorApp.facility.status">Status</Translate>
            </span>
          </dt>
          <dd>{facilityEntity.status}</dd>
        </dl>
        <Button tag={Link} to="/facility" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/facility/${facilityEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FacilityDetail;
