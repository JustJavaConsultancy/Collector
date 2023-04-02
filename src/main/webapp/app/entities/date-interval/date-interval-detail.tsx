import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './date-interval.reducer';

export const DateIntervalDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const dateIntervalEntity = useAppSelector(state => state.collector.dateInterval.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dateIntervalDetailsHeading">
          <Translate contentKey="collectorApp.dateInterval.detail.title">DateInterval</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dateIntervalEntity.id}</dd>
          <dt>
            <span id="interval">
              <Translate contentKey="collectorApp.dateInterval.interval">Interval</Translate>
            </span>
          </dt>
          <dd>{dateIntervalEntity.interval}</dd>
          <dt>
            <span id="measure">
              <Translate contentKey="collectorApp.dateInterval.measure">Measure</Translate>
            </span>
          </dt>
          <dd>{dateIntervalEntity.measure}</dd>
        </dl>
        <Button tag={Link} to="/date-interval" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/date-interval/${dateIntervalEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DateIntervalDetail;
