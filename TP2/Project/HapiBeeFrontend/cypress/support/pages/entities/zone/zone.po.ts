import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class ZoneComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-zone';
}

export class ZoneUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-zone-update';

  setNameInput(name: string) {
    this.setInputValue('name', name);
  }

  setNumberInput(number: string) {
    this.setInputValue('number', number);
  }

  setCoordXInput(coordX: string) {
    this.setInputValue('coordX', coordX);
  }

  setCoordYInput(coordY: string) {
    this.setInputValue('coordY', coordY);
  }

  setCoordZInput(coordZ: string) {
    this.setInputValue('coordZ', coordZ);
  }

  setControlledZoneInput(controlledZone: string) {
    this.setBoolean('controlledZone', controlledZone);
  }
}

export class ZoneDetailPage extends EntityDetailPage {
  pageSelector = 'page-zone-detail';

  getNameContent() {
    return cy.get('#name-content');
  }

  getNumberContent() {
    return cy.get('#number-content');
  }

  getCoordXContent() {
    return cy.get('#coordX-content');
  }

  getCoordYContent() {
    return cy.get('#coordY-content');
  }

  getCoordZContent() {
    return cy.get('#coordZ-content');
  }

  getControlledZoneContent() {
    return cy.get('#controlledZone-content');
  }
}
