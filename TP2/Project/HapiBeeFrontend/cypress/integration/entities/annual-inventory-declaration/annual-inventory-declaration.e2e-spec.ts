import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import {
  AnnualInventoryDeclarationComponentsPage,
  AnnualInventoryDeclarationDetailPage,
  AnnualInventoryDeclarationUpdatePage,
} from '../../../support/pages/entities/annual-inventory-declaration/annual-inventory-declaration.po';
import annualInventoryDeclarationSample from './annual-inventory-declaration.json';

describe('AnnualInventoryDeclaration entity', () => {
  const COMPONENT_TITLE = 'Annual Inventory Declarations';
  const SUBCOMPONENT_TITLE = 'Annual Inventory Declaration';

  const annualInventoryDeclarationPageUrl = '/tabs/entities/annual-inventory-declaration';
  const annualInventoryDeclarationApiUrl = '/api/annual-inventory-declarations';

  const annualInventoryDeclarationComponentsPage = new AnnualInventoryDeclarationComponentsPage();
  const annualInventoryDeclarationUpdatePage = new AnnualInventoryDeclarationUpdatePage();
  const annualInventoryDeclarationDetailPage = new AnnualInventoryDeclarationDetailPage();

  let annualInventoryDeclaration: any;

  beforeEach(() => {
    annualInventoryDeclaration = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load AnnualInventoryDeclarations page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      annualInventoryDeclarationComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', annualInventoryDeclarationPageUrl);

      annualInventoryDeclarationComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create AnnualInventoryDeclaration page and go back', () => {
      cy.visit(annualInventoryDeclarationPageUrl);
      annualInventoryDeclarationComponentsPage.clickOnCreateButton();

      annualInventoryDeclarationUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      annualInventoryDeclarationUpdatePage.back();
      cy.url().should('include', annualInventoryDeclarationPageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: annualInventoryDeclarationApiUrl,
        body: annualInventoryDeclarationSample,
      }).then(({ body }) => {
        annualInventoryDeclaration = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${annualInventoryDeclarationApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [annualInventoryDeclaration],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (annualInventoryDeclaration) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${annualInventoryDeclarationApiUrl}/${annualInventoryDeclaration.id}`,
        }).then(() => {
          annualInventoryDeclaration = undefined;
        });
      }
    });

    it('should open AnnualInventoryDeclaration view, open AnnualInventoryDeclaration edit and go back', () => {
      cy.visit(annualInventoryDeclarationPageUrl);
      annualInventoryDeclarationComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      annualInventoryDeclarationDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      if (annualInventoryDeclaration.total !== undefined && annualInventoryDeclaration.total !== null) {
        annualInventoryDeclarationDetailPage.getTotalContent().contains(annualInventoryDeclaration.total);
      }
      if (annualInventoryDeclaration.beekeeperFaxNumber !== undefined && annualInventoryDeclaration.beekeeperFaxNumber !== null) {
        annualInventoryDeclarationDetailPage.getBeekeeperFaxNumberContent().contains(annualInventoryDeclaration.beekeeperFaxNumber);
      }
      if (annualInventoryDeclaration.beekeeperCompleteName !== undefined && annualInventoryDeclaration.beekeeperCompleteName !== null) {
        annualInventoryDeclarationDetailPage.getBeekeeperCompleteNameContent().contains(annualInventoryDeclaration.beekeeperCompleteName);
      }
      if (annualInventoryDeclaration.beekeeperNif !== undefined && annualInventoryDeclaration.beekeeperNif !== null) {
        annualInventoryDeclarationDetailPage.getBeekeeperNifContent().contains(annualInventoryDeclaration.beekeeperNif);
      }
      if (annualInventoryDeclaration.beekeeperAddress !== undefined && annualInventoryDeclaration.beekeeperAddress !== null) {
        annualInventoryDeclarationDetailPage.getBeekeeperAddressContent().contains(annualInventoryDeclaration.beekeeperAddress);
      }
      if (annualInventoryDeclaration.beekeeperPostalCode !== undefined && annualInventoryDeclaration.beekeeperPostalCode !== null) {
        annualInventoryDeclarationDetailPage.getBeekeeperPostalCodeContent().contains(annualInventoryDeclaration.beekeeperPostalCode);
      }
      if (annualInventoryDeclaration.beekeeperPhoneNumber !== undefined && annualInventoryDeclaration.beekeeperPhoneNumber !== null) {
        annualInventoryDeclarationDetailPage.getBeekeeperPhoneNumberContent().contains(annualInventoryDeclaration.beekeeperPhoneNumber);
      }
      if (
        annualInventoryDeclaration.beekeeperEntityZoneResidence !== undefined &&
        annualInventoryDeclaration.beekeeperEntityZoneResidence !== null
      ) {
        annualInventoryDeclarationDetailPage
          .getBeekeeperEntityZoneResidenceContent()
          .contains(annualInventoryDeclaration.beekeeperEntityZoneResidence);
      }
      if (annualInventoryDeclaration.beekeeperNumber !== undefined && annualInventoryDeclaration.beekeeperNumber !== null) {
        annualInventoryDeclarationDetailPage.getBeekeeperNumberContent().contains(annualInventoryDeclaration.beekeeperNumber);
      }
      if (annualInventoryDeclaration.revisionLocation !== undefined && annualInventoryDeclaration.revisionLocation !== null) {
        annualInventoryDeclarationDetailPage.getRevisionLocationContent().contains(annualInventoryDeclaration.revisionLocation);
      }
      if (annualInventoryDeclaration.revisorSignature !== undefined && annualInventoryDeclaration.revisorSignature !== null) {
        annualInventoryDeclarationDetailPage.getRevisorSignatureContent().contains(annualInventoryDeclaration.revisorSignature);
      }
      if (annualInventoryDeclaration.revisorName !== undefined && annualInventoryDeclaration.revisorName !== null) {
        annualInventoryDeclarationDetailPage.getRevisorNameContent().contains(annualInventoryDeclaration.revisorName);
      }
      annualInventoryDeclarationDetailPage.edit();

      annualInventoryDeclarationUpdatePage.back();
      annualInventoryDeclarationDetailPage.back();
      cy.url().should('include', annualInventoryDeclarationPageUrl);
    });

    it('should open AnnualInventoryDeclaration view, open AnnualInventoryDeclaration edit and save', () => {
      cy.visit(annualInventoryDeclarationPageUrl);
      annualInventoryDeclarationComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      annualInventoryDeclarationDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      annualInventoryDeclarationDetailPage.edit();

      annualInventoryDeclarationUpdatePage.save();
      cy.url().should('include', annualInventoryDeclarationPageUrl);
    });

    it('should delete AnnualInventoryDeclaration', () => {
      cy.visit(annualInventoryDeclarationPageUrl);
      annualInventoryDeclarationComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      annualInventoryDeclarationDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      annualInventoryDeclarationComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      annualInventoryDeclaration = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: annualInventoryDeclarationApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (annualInventoryDeclaration) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${annualInventoryDeclarationApiUrl}/${annualInventoryDeclaration.id}`,
        }).then(() => {
          annualInventoryDeclaration = undefined;
        });
      }
    });

    it('should create AnnualInventoryDeclaration', () => {
      cy.visit(annualInventoryDeclarationPageUrl + '/new');

      annualInventoryDeclarationUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (annualInventoryDeclarationSample.total !== undefined && annualInventoryDeclarationSample.total !== null) {
        annualInventoryDeclarationUpdatePage.setTotalInput(annualInventoryDeclarationSample.total);
      }
      if (
        annualInventoryDeclarationSample.beekeeperFaxNumber !== undefined &&
        annualInventoryDeclarationSample.beekeeperFaxNumber !== null
      ) {
        annualInventoryDeclarationUpdatePage.setBeekeeperFaxNumberInput(annualInventoryDeclarationSample.beekeeperFaxNumber);
      }
      if (
        annualInventoryDeclarationSample.beekeeperCompleteName !== undefined &&
        annualInventoryDeclarationSample.beekeeperCompleteName !== null
      ) {
        annualInventoryDeclarationUpdatePage.setBeekeeperCompleteNameInput(annualInventoryDeclarationSample.beekeeperCompleteName);
      }
      if (annualInventoryDeclarationSample.beekeeperNif !== undefined && annualInventoryDeclarationSample.beekeeperNif !== null) {
        annualInventoryDeclarationUpdatePage.setBeekeeperNifInput(annualInventoryDeclarationSample.beekeeperNif);
      }
      if (annualInventoryDeclarationSample.date !== undefined && annualInventoryDeclarationSample.date !== null) {
        annualInventoryDeclarationUpdatePage.setDateInput(annualInventoryDeclarationSample.date);
      }
      if (annualInventoryDeclarationSample.beekeeperAddress !== undefined && annualInventoryDeclarationSample.beekeeperAddress !== null) {
        annualInventoryDeclarationUpdatePage.setBeekeeperAddressInput(annualInventoryDeclarationSample.beekeeperAddress);
      }
      if (
        annualInventoryDeclarationSample.beekeeperPostalCode !== undefined &&
        annualInventoryDeclarationSample.beekeeperPostalCode !== null
      ) {
        annualInventoryDeclarationUpdatePage.setBeekeeperPostalCodeInput(annualInventoryDeclarationSample.beekeeperPostalCode);
      }
      if (
        annualInventoryDeclarationSample.beekeeperPhoneNumber !== undefined &&
        annualInventoryDeclarationSample.beekeeperPhoneNumber !== null
      ) {
        annualInventoryDeclarationUpdatePage.setBeekeeperPhoneNumberInput(annualInventoryDeclarationSample.beekeeperPhoneNumber);
      }
      if (
        annualInventoryDeclarationSample.beekeeperEntityZoneResidence !== undefined &&
        annualInventoryDeclarationSample.beekeeperEntityZoneResidence !== null
      ) {
        annualInventoryDeclarationUpdatePage.setBeekeeperEntityZoneResidenceInput(
          annualInventoryDeclarationSample.beekeeperEntityZoneResidence
        );
      }
      if (annualInventoryDeclarationSample.beekeeperNumber !== undefined && annualInventoryDeclarationSample.beekeeperNumber !== null) {
        annualInventoryDeclarationUpdatePage.setBeekeeperNumberInput(annualInventoryDeclarationSample.beekeeperNumber);
      }
      if (
        annualInventoryDeclarationSample.annualInventoryDeclarationState !== undefined &&
        annualInventoryDeclarationSample.annualInventoryDeclarationState !== null
      ) {
        annualInventoryDeclarationUpdatePage.setAnnualInventoryDeclarationStateInput(
          annualInventoryDeclarationSample.annualInventoryDeclarationState
        );
      }
      if (annualInventoryDeclarationSample.revisionDate !== undefined && annualInventoryDeclarationSample.revisionDate !== null) {
        annualInventoryDeclarationUpdatePage.setRevisionDateInput(annualInventoryDeclarationSample.revisionDate);
      }
      if (annualInventoryDeclarationSample.revisionLocation !== undefined && annualInventoryDeclarationSample.revisionLocation !== null) {
        annualInventoryDeclarationUpdatePage.setRevisionLocationInput(annualInventoryDeclarationSample.revisionLocation);
      }
      if (annualInventoryDeclarationSample.revisorSignature !== undefined && annualInventoryDeclarationSample.revisorSignature !== null) {
        annualInventoryDeclarationUpdatePage.setRevisorSignatureInput(annualInventoryDeclarationSample.revisorSignature);
      }
      if (annualInventoryDeclarationSample.revisorName !== undefined && annualInventoryDeclarationSample.revisorName !== null) {
        annualInventoryDeclarationUpdatePage.setRevisorNameInput(annualInventoryDeclarationSample.revisorName);
      }
      annualInventoryDeclarationUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        annualInventoryDeclaration = body;
      });

      annualInventoryDeclarationComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
