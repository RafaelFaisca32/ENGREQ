import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class ApiaryComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-apiary';
}

export class ApiaryUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-apiary-update';

  setNameInput(name: string) {
    this.setInputValue('name', name);
  }

  setStateInput(state: string) {
    this.select('state', state);
  }

  setNumberInput(number: string) {
    this.setInputValue('number', number);
  }

  setIntensiveInput(intensive: string) {
    this.setBoolean('intensive', intensive);
  }

  setTranshumanceInput(transhumance: string) {
    this.setBoolean('transhumance', transhumance);
  }
}

export class ApiaryDetailPage extends EntityDetailPage {
  pageSelector = 'page-apiary-detail';

  getNameContent() {
    return cy.get('#name-content');
  }

  getStateContent() {
    return cy.get('#state-content');
  }

  getNumberContent() {
    return cy.get('#number-content');
  }

  getIntensiveContent() {
    return cy.get('#intensive-content');
  }

  getTranshumanceContent() {
    return cy.get('#transhumance-content');
  }
}
