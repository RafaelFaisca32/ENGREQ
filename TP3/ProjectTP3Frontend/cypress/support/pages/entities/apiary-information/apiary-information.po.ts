import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class ApiaryInformationComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-apiary-information';
}

export class ApiaryInformationUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-apiary-information-update';

  setZoneNumberInput(zoneNumber: string) {
    this.setInputValue('zoneNumber', zoneNumber);
  }

  setZoneNameInput(zoneName: string) {
    this.setInputValue('zoneName', zoneName);
  }

  setNumberHivesInput(numberHives: string) {
    this.setInputValue('numberHives', numberHives);
  }

  setIntensiveInput(intensive: string) {
    this.setBoolean('intensive', intensive);
  }

  setTranshumanceInput(transhumance: string) {
    this.setBoolean('transhumance', transhumance);
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

  setNumberFramesInput(numberFrames: string) {
    this.setInputValue('numberFrames', numberFrames);
  }
}

export class ApiaryInformationDetailPage extends EntityDetailPage {
  pageSelector = 'page-apiary-information-detail';

  getZoneNumberContent() {
    return cy.get('#zoneNumber-content');
  }

  getZoneNameContent() {
    return cy.get('#zoneName-content');
  }

  getNumberHivesContent() {
    return cy.get('#numberHives-content');
  }

  getIntensiveContent() {
    return cy.get('#intensive-content');
  }

  getTranshumanceContent() {
    return cy.get('#transhumance-content');
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

  getNumberFramesContent() {
    return cy.get('#numberFrames-content');
  }
}
