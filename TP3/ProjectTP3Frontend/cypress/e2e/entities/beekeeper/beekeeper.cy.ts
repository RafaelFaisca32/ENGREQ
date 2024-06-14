import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import { BeekeeperComponentsPage, BeekeeperDetailPage, BeekeeperUpdatePage } from '../../../support/pages/entities/beekeeper/beekeeper.po';
import beekeeperSample from './beekeeper.json';

describe('Beekeeper entity', () => {
  const COMPONENT_TITLE = 'Beekeepers';
  const SUBCOMPONENT_TITLE = 'Beekeeper';

  const beekeeperPageUrl = '/tabs/entities/beekeeper';
  const beekeeperApiUrl = '/api/beekeepers';

  const beekeeperComponentsPage = new BeekeeperComponentsPage();
  const beekeeperUpdatePage = new BeekeeperUpdatePage();
  const beekeeperDetailPage = new BeekeeperDetailPage();

  let beekeeper: any;

  beforeEach(() => {
    beekeeper = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load Beekeepers page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      beekeeperComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', beekeeperPageUrl);

      beekeeperComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create Beekeeper page and go back', () => {
      cy.visit(beekeeperPageUrl);
      beekeeperComponentsPage.clickOnCreateButton();

      beekeeperUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      beekeeperUpdatePage.back();
      cy.url().should('include', beekeeperPageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: beekeeperApiUrl,
        body: beekeeperSample,
      }).then(({ body }) => {
        beekeeper = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${beekeeperApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [beekeeper],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (beekeeper) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${beekeeperApiUrl}/${beekeeper.id}`,
        }).then(() => {
          beekeeper = undefined;
        });
      }
    });

    it('should open Beekeeper view, open Beekeeper edit and go back', () => {
      cy.visit(beekeeperPageUrl);
      beekeeperComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      beekeeperDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      if (beekeeper.beekeeperCompleteName !== undefined && beekeeper.beekeeperCompleteName !== null) {
        beekeeperDetailPage.getBeekeeperCompleteNameContent().contains(beekeeper.beekeeperCompleteName);
      }
      if (beekeeper.beekeeperNumber !== undefined && beekeeper.beekeeperNumber !== null) {
        beekeeperDetailPage.getBeekeeperNumberContent().contains(beekeeper.beekeeperNumber);
      }
      if (beekeeper.entityZoneResidence !== undefined && beekeeper.entityZoneResidence !== null) {
        beekeeperDetailPage.getEntityZoneResidenceContent().contains(beekeeper.entityZoneResidence);
      }
      if (beekeeper.nif !== undefined && beekeeper.nif !== null) {
        beekeeperDetailPage.getNifContent().contains(beekeeper.nif);
      }
      if (beekeeper.address !== undefined && beekeeper.address !== null) {
        beekeeperDetailPage.getAddressContent().contains(beekeeper.address);
      }
      if (beekeeper.postalCode !== undefined && beekeeper.postalCode !== null) {
        beekeeperDetailPage.getPostalCodeContent().contains(beekeeper.postalCode);
      }
      if (beekeeper.phoneNumber !== undefined && beekeeper.phoneNumber !== null) {
        beekeeperDetailPage.getPhoneNumberContent().contains(beekeeper.phoneNumber);
      }
      if (beekeeper.faxNumber !== undefined && beekeeper.faxNumber !== null) {
        beekeeperDetailPage.getFaxNumberContent().contains(beekeeper.faxNumber);
      }
      beekeeperDetailPage.edit();

      beekeeperUpdatePage.back();
      beekeeperDetailPage.back();
      cy.url().should('include', beekeeperPageUrl);
    });

    it('should open Beekeeper view, open Beekeeper edit and save', () => {
      cy.visit(beekeeperPageUrl);
      beekeeperComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      beekeeperDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      beekeeperDetailPage.edit();

      beekeeperUpdatePage.save();
      cy.url().should('include', beekeeperPageUrl);
    });

    it('should delete Beekeeper', () => {
      cy.visit(beekeeperPageUrl);
      beekeeperComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      beekeeperDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      beekeeperComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      beekeeper = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: beekeeperApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (beekeeper) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${beekeeperApiUrl}/${beekeeper.id}`,
        }).then(() => {
          beekeeper = undefined;
        });
      }
    });

    it('should create Beekeeper', () => {
      cy.visit(beekeeperPageUrl + '/new');

      beekeeperUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (beekeeperSample.beekeeperCompleteName !== undefined && beekeeperSample.beekeeperCompleteName !== null) {
        beekeeperUpdatePage.setBeekeeperCompleteNameInput(beekeeperSample.beekeeperCompleteName);
      }
      if (beekeeperSample.beekeeperNumber !== undefined && beekeeperSample.beekeeperNumber !== null) {
        beekeeperUpdatePage.setBeekeeperNumberInput(beekeeperSample.beekeeperNumber);
      }
      if (beekeeperSample.entityZoneResidence !== undefined && beekeeperSample.entityZoneResidence !== null) {
        beekeeperUpdatePage.setEntityZoneResidenceInput(beekeeperSample.entityZoneResidence);
      }
      if (beekeeperSample.nif !== undefined && beekeeperSample.nif !== null) {
        beekeeperUpdatePage.setNifInput(beekeeperSample.nif);
      }
      if (beekeeperSample.address !== undefined && beekeeperSample.address !== null) {
        beekeeperUpdatePage.setAddressInput(beekeeperSample.address);
      }
      if (beekeeperSample.postalCode !== undefined && beekeeperSample.postalCode !== null) {
        beekeeperUpdatePage.setPostalCodeInput(beekeeperSample.postalCode);
      }
      if (beekeeperSample.phoneNumber !== undefined && beekeeperSample.phoneNumber !== null) {
        beekeeperUpdatePage.setPhoneNumberInput(beekeeperSample.phoneNumber);
      }
      if (beekeeperSample.faxNumber !== undefined && beekeeperSample.faxNumber !== null) {
        beekeeperUpdatePage.setFaxNumberInput(beekeeperSample.faxNumber);
      }
      if (beekeeperSample.state !== undefined && beekeeperSample.state !== null) {
        beekeeperUpdatePage.setStateInput(beekeeperSample.state);
      }
      beekeeperUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        beekeeper = body;
      });

      beekeeperComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
