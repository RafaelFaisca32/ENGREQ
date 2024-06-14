import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class AnnualInventoryDeclarationComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-annual-inventory-declaration';
}

export class AnnualInventoryDeclarationUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-annual-inventory-declaration-update';

  setTotalInput(total: string) {
    this.setInputValue('total', total);
  }

  setBeekeeperFaxNumberInput(beekeeperFaxNumber: string) {
    this.setInputValue('beekeeperFaxNumber', beekeeperFaxNumber);
  }

  setBeekeeperCompleteNameInput(beekeeperCompleteName: string) {
    this.setInputValue('beekeeperCompleteName', beekeeperCompleteName);
  }

  setBeekeeperNifInput(beekeeperNif: string) {
    this.setInputValue('beekeeperNif', beekeeperNif);
  }

  setDateInput(date: string) {
    this.setDate('date', date);
  }

  setBeekeeperAddressInput(beekeeperAddress: string) {
    this.setInputValue('beekeeperAddress', beekeeperAddress);
  }

  setBeekeeperPostalCodeInput(beekeeperPostalCode: string) {
    this.setInputValue('beekeeperPostalCode', beekeeperPostalCode);
  }

  setBeekeeperPhoneNumberInput(beekeeperPhoneNumber: string) {
    this.setInputValue('beekeeperPhoneNumber', beekeeperPhoneNumber);
  }

  setBeekeeperEntityZoneResidenceInput(beekeeperEntityZoneResidence: string) {
    this.setInputValue('beekeeperEntityZoneResidence', beekeeperEntityZoneResidence);
  }

  setBeekeeperNumberInput(beekeeperNumber: string) {
    this.setInputValue('beekeeperNumber', beekeeperNumber);
  }

  setAnnualInventoryDeclarationStateInput(annualInventoryDeclarationState: string) {
    this.select('annualInventoryDeclarationState', annualInventoryDeclarationState);
  }

  setRevisionDateInput(revisionDate: string) {
    this.setDate('revisionDate', revisionDate);
  }

  setRevisionLocationInput(revisionLocation: string) {
    this.setInputValue('revisionLocation', revisionLocation);
  }

  setRevisorSignatureInput(revisorSignature: string) {
    this.setInputValue('revisorSignature', revisorSignature);
  }

  setRevisorNameInput(revisorName: string) {
    this.setInputValue('revisorName', revisorName);
  }
}

export class AnnualInventoryDeclarationDetailPage extends EntityDetailPage {
  pageSelector = 'page-annual-inventory-declaration-detail';

  getTotalContent() {
    return cy.get('#total-content');
  }

  getBeekeeperFaxNumberContent() {
    return cy.get('#beekeeperFaxNumber-content');
  }

  getBeekeeperCompleteNameContent() {
    return cy.get('#beekeeperCompleteName-content');
  }

  getBeekeeperNifContent() {
    return cy.get('#beekeeperNif-content');
  }

  getDateContent() {
    return cy.get('#date-content');
  }

  getBeekeeperAddressContent() {
    return cy.get('#beekeeperAddress-content');
  }

  getBeekeeperPostalCodeContent() {
    return cy.get('#beekeeperPostalCode-content');
  }

  getBeekeeperPhoneNumberContent() {
    return cy.get('#beekeeperPhoneNumber-content');
  }

  getBeekeeperEntityZoneResidenceContent() {
    return cy.get('#beekeeperEntityZoneResidence-content');
  }

  getBeekeeperNumberContent() {
    return cy.get('#beekeeperNumber-content');
  }

  getAnnualInventoryDeclarationStateContent() {
    return cy.get('#annualInventoryDeclarationState-content');
  }

  getRevisionDateContent() {
    return cy.get('#revisionDate-content');
  }

  getRevisionLocationContent() {
    return cy.get('#revisionLocation-content');
  }

  getRevisorSignatureContent() {
    return cy.get('#revisorSignature-content');
  }

  getRevisorNameContent() {
    return cy.get('#revisorName-content');
  }
}
