import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DateInterval from './date-interval';
import DateIntervalDetail from './date-interval-detail';
import DateIntervalUpdate from './date-interval-update';
import DateIntervalDeleteDialog from './date-interval-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DateIntervalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DateIntervalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DateIntervalDetail} />
      <ErrorBoundaryRoute path={match.url} component={DateInterval} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DateIntervalDeleteDialog} />
  </>
);

export default Routes;
