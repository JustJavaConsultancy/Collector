import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './subscriber.reducer';

export const SubscriberDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const subscriberEntity = useAppSelector(state => state.collector.subscriber.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="subscriberDetailsHeading">
          <Translate contentKey="collectorApp.subscriber.detail.title">Subscriber</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{subscriberEntity.id}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="collectorApp.subscriber.lastName">Last Name</Translate>
            </span>
          </dt>
          <dd>{subscriberEntity.lastName}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="collectorApp.subscriber.firstName">First Name</Translate>
            </span>
          </dt>
          <dd>{subscriberEntity.firstName}</dd>
          <dt>
            <span id="middleName">
              <Translate contentKey="collectorApp.subscriber.middleName">Middle Name</Translate>
            </span>
            <UncontrolledTooltip target="middleName">
              <Translate contentKey="collectorApp.subscriber.help.middleName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{subscriberEntity.middleName}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="collectorApp.subscriber.type">Type</Translate>
            </span>
          </dt>
          <dd>{subscriberEntity.type}</dd>
          <dt>
            <span id="dateOfBirth">
              <Translate contentKey="collectorApp.subscriber.dateOfBirth">Date Of Birth</Translate>
            </span>
          </dt>
          <dd>
            {subscriberEntity.dateOfBirth ? (
              <TextFormat value={subscriberEntity.dateOfBirth} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="collectorApp.subscriber.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{subscriberEntity.phoneNumber}</dd>
          <dt>
            <span id="idNumber">
              <Translate contentKey="collectorApp.subscriber.idNumber">Id Number</Translate>
            </span>
          </dt>
          <dd>{subscriberEntity.idNumber}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="collectorApp.subscriber.status">Status</Translate>
            </span>
          </dt>
          <dd>{subscriberEntity.status}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="collectorApp.subscriber.address">Address</Translate>
            </span>
          </dt>
          <dd>{subscriberEntity.address}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="collectorApp.subscriber.email">Email</Translate>
            </span>
          </dt>
          <dd>{subscriberEntity.email}</dd>
          <dt>
            <span id="dateRegistered">
              <Translate contentKey="collectorApp.subscriber.dateRegistered">Date Registered</Translate>
            </span>
          </dt>
          <dd>
            {subscriberEntity.dateRegistered ? (
              <TextFormat value={subscriberEntity.dateRegistered} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/subscriber" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/subscriber/${subscriberEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SubscriberDetail;
