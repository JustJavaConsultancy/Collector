import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IFacility } from 'app/shared/model/facility.model';
import { getEntities } from './facility.reducer';

export const Facility = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const facilityList = useAppSelector(state => state.collector.facility.entities);
  const loading = useAppSelector(state => state.collector.facility.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="facility-heading" data-cy="FacilityHeading">
        <Translate contentKey="collectorApp.facility.home.title">Facilities</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="collectorApp.facility.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/facility/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="collectorApp.facility.home.createLabel">Create new Facility</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {facilityList && facilityList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="collectorApp.facility.id">Id</Translate>
                </th>
                <th>
                  <Translate contentKey="collectorApp.facility.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="collectorApp.facility.userType">User Type</Translate>
                </th>
                <th>
                  <Translate contentKey="collectorApp.facility.rentCycle">Rent Cycle</Translate>
                </th>
                <th>
                  <Translate contentKey="collectorApp.facility.location">Location</Translate>
                </th>
                <th>
                  <Translate contentKey="collectorApp.facility.refNumber">Ref Number</Translate>
                </th>
                <th>
                  <Translate contentKey="collectorApp.facility.size">Size</Translate>
                </th>
                <th>
                  <Translate contentKey="collectorApp.facility.dateRegistered">Date Registered</Translate>
                </th>
                <th>
                  <Translate contentKey="collectorApp.facility.status">Status</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {facilityList.map((facility, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/facility/${facility.id}`} color="link" size="sm">
                      {facility.id}
                    </Button>
                  </td>
                  <td>{facility.description}</td>
                  <td>
                    <Translate contentKey={`collectorApp.UserTypeEnum.${facility.userType}`} />
                  </td>
                  <td>
                    <Translate contentKey={`collectorApp.RentCycleEnum.${facility.rentCycle}`} />
                  </td>
                  <td>{facility.location}</td>
                  <td>{facility.refNumber}</td>
                  <td>{facility.size}</td>
                  <td>
                    {facility.dateRegistered ? (
                      <TextFormat type="date" value={facility.dateRegistered} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    <Translate contentKey={`collectorApp.StatusEnum.${facility.status}`} />
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/facility/${facility.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/facility/${facility.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/facility/${facility.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="collectorApp.facility.home.notFound">No Facilities found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Facility;
