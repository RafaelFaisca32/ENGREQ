import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class CrestComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-crest';
}

export class CrestUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-crest-update';

  setCombFrameQuantityInput(combFrameQuantity: string) {
    this.setInputValue('combFrameQuantity', combFrameQuantity);
  }

  setWaxWeightInput(waxWeight: string) {
    this.setInputValue('waxWeight', waxWeight);
  }

  setTimeWastedCentrifugeInput(timeWastedCentrifuge: string) {
    this.setInputValue('timeWastedCentrifuge', timeWastedCentrifuge);
  }

  setInitialDateDecantationInput(initialDateDecantation: string) {
    this.setDateTime('initialDateDecantation', initialDateDecantation);
  }

  setFinalDateDecantationInput(finalDateDecantation: string) {
    this.setDateTime('finalDateDecantation', finalDateDecantation);
  }

  setProducedHoneyQuantityInput(producedHoneyQuantity: string) {
    this.setInputValue('producedHoneyQuantity', producedHoneyQuantity);
  }

  setStateInput(state: string) {
    this.select('state', state);
  }
}

export class CrestDetailPage extends EntityDetailPage {
  pageSelector = 'page-crest-detail';

  getCombFrameQuantityContent() {
    return cy.get('#combFrameQuantity-content');
  }

  getWaxWeightContent() {
    return cy.get('#waxWeight-content');
  }

  getTimeWastedCentrifugeContent() {
    return cy.get('#timeWastedCentrifuge-content');
  }

  getInitialDateDecantationContent() {
    return cy.get('#initialDateDecantation-content');
  }

  getFinalDateDecantationContent() {
    return cy.get('#finalDateDecantation-content');
  }

  getProducedHoneyQuantityContent() {
    return cy.get('#producedHoneyQuantity-content');
  }

  getStateContent() {
    return cy.get('#state-content');
  }
}
