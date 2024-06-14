import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class ApiaryZoneComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-apiary-zone';
}

export class ApiaryZoneUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-apiary-zone-update';

  setNameInput(name: string) {
    this.setInputValue('name', name);
  }

  setControlledZoneInput(controlledZone: string) {
    this.setBoolean('controlledZone', controlledZone);
  }

  setStateInput(state: string) {
    this.select('state', state);
  }
}

export class ApiaryZoneDetailPage extends EntityDetailPage {
  pageSelector = 'page-apiary-zone-detail';

  getNameContent() {
    return cy.get('#name-content');
  }

  getControlledZoneContent() {
    return cy.get('#controlledZone-content');
  }

  getStateContent() {
    return cy.get('#state-content');
  }
}
