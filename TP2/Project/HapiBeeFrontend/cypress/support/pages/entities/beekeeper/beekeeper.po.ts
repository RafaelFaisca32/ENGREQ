import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class BeekeeperComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-beekeeper';
}

export class BeekeeperUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-beekeeper-update';

  setBeekeeperCompleteNameInput(beekeeperCompleteName: string) {
    this.setInputValue('beekeeperCompleteName', beekeeperCompleteName);
  }

  setBeekeeperNumberInput(beekeeperNumber: string) {
    this.setInputValue('beekeeperNumber', beekeeperNumber);
  }

  setEntityZoneResidenceInput(entityZoneResidence: string) {
    this.setInputValue('entityZoneResidence', entityZoneResidence);
  }

  setNifInput(nif: string) {
    this.setInputValue('nif', nif);
  }

  setAddressInput(address: string) {
    this.setInputValue('address', address);
  }

  setPostalCodeInput(postalCode: string) {
    this.setInputValue('postalCode', postalCode);
  }

  setPhoneNumberInput(phoneNumber: string) {
    this.setInputValue('phoneNumber', phoneNumber);
  }

  setFaxNumberInput(faxNumber: string) {
    this.setInputValue('faxNumber', faxNumber);
  }
}

export class BeekeeperDetailPage extends EntityDetailPage {
  pageSelector = 'page-beekeeper-detail';

  getBeekeeperCompleteNameContent() {
    return cy.get('#beekeeperCompleteName-content');
  }

  getBeekeeperNumberContent() {
    return cy.get('#beekeeperNumber-content');
  }

  getEntityZoneResidenceContent() {
    return cy.get('#entityZoneResidence-content');
  }

  getNifContent() {
    return cy.get('#nif-content');
  }

  getAddressContent() {
    return cy.get('#address-content');
  }

  getPostalCodeContent() {
    return cy.get('#postalCode-content');
  }

  getPhoneNumberContent() {
    return cy.get('#phoneNumber-content');
  }

  getFaxNumberContent() {
    return cy.get('#faxNumber-content');
  }
}
