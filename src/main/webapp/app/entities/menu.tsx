import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/subscriber">
        <Translate contentKey="global.menu.entities.subscriber" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/facility">
        <Translate contentKey="global.menu.entities.facility" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/date-interval">
        <Translate contentKey="global.menu.entities.dateInterval" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu as React.ComponentType<any>;
