import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDateInterval } from 'app/shared/model/date-interval.model';
import { getEntities } from './date-interval.reducer';

export const DateInterval = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const dateIntervalList = useAppSelector(state => state.collector.dateInterval.entities);
  const loading = useAppSelector(state => state.collector.dateInterval.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="date-interval-heading" data-cy="DateIntervalHeading">
        <Translate contentKey="collectorApp.dateInterval.home.title">Date Intervals</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="collectorApp.dateInterval.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/date-interval/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="collectorApp.dateInterval.home.createLabel">Create new Date Interval</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {dateIntervalList && dateIntervalList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="collectorApp.dateInterval.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="collectorApp.dateInterval.interval">Interval</Translate>
                </th>
                <th>
                  <Translate contentKey="collectorApp.dateInterval.measure">Measure</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dateIntervalList.map((dateInterval, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/date-interval/${dateInterval.id}`} color="link" size="sm">
                      {dateInterval.id}
                    </Button>
                  </td>
                  <td>{dateInterval.interval}</td>
                  <td>
                    <Translate contentKey={`collectorApp.DateIntervalMeasureEnum.${dateInterval.measure}`} />
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/date-interval/${dateInterval.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/date-interval/${dateInterval.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/date-interval/${dateInterval.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="collectorApp.dateInterval.home.notFound">No Date Intervals found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DateInterval;
