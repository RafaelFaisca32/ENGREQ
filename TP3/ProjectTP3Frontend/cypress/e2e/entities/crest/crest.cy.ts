import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import { CrestComponentsPage, CrestDetailPage, CrestUpdatePage } from '../../../support/pages/entities/crest/crest.po';
import crestSample from './crest.json';

describe('Crest entity', () => {
  const COMPONENT_TITLE = 'Crests';
  const SUBCOMPONENT_TITLE = 'Crest';

  const crestPageUrl = '/tabs/entities/crest';
  const crestApiUrl = '/api/crests';

  const crestComponentsPage = new CrestComponentsPage();
  const crestUpdatePage = new CrestUpdatePage();
  const crestDetailPage = new CrestDetailPage();

  let crest: any;

  beforeEach(() => {
    crest = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load Crests page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      crestComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', crestPageUrl);

      crestComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create Crest page and go back', () => {
      cy.visit(crestPageUrl);
      crestComponentsPage.clickOnCreateButton();

      crestUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      crestUpdatePage.back();
      cy.url().should('include', crestPageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: crestApiUrl,
        body: crestSample,
      }).then(({ body }) => {
        crest = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${crestApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [crest],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (crest) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${crestApiUrl}/${crest.id}`,
        }).then(() => {
          crest = undefined;
        });
      }
    });

    it('should open Crest view, open Crest edit and go back', () => {
      cy.visit(crestPageUrl);
      crestComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      crestDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      if (crest.waxWeight !== undefined && crest.waxWeight !== null) {
        crestDetailPage.getWaxWeightContent().contains(crest.waxWeight);
      }
      if (crest.timeWastedCentrifuge !== undefined && crest.timeWastedCentrifuge !== null) {
        crestDetailPage.getTimeWastedCentrifugeContent().contains(crest.timeWastedCentrifuge);
      }
      if (crest.producedHoneyQuantity !== undefined && crest.producedHoneyQuantity !== null) {
        crestDetailPage.getProducedHoneyQuantityContent().contains(crest.producedHoneyQuantity);
      }
      crestDetailPage.edit();

      crestUpdatePage.back();
      crestDetailPage.back();
      cy.url().should('include', crestPageUrl);
    });

    it('should open Crest view, open Crest edit and save', () => {
      cy.visit(crestPageUrl);
      crestComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      crestDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      crestDetailPage.edit();

      crestUpdatePage.save();
      cy.url().should('include', crestPageUrl);
    });

    it('should delete Crest', () => {
      cy.visit(crestPageUrl);
      crestComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      crestDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      crestComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      crest = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: crestApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (crest) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${crestApiUrl}/${crest.id}`,
        }).then(() => {
          crest = undefined;
        });
      }
    });

    it('should create Crest', () => {
      cy.visit(crestPageUrl + '/new');

      crestUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (crestSample.waxWeight !== undefined && crestSample.waxWeight !== null) {
        crestUpdatePage.setWaxWeightInput(crestSample.waxWeight);
      }
      if (crestSample.timeWastedCentrifuge !== undefined && crestSample.timeWastedCentrifuge !== null) {
        crestUpdatePage.setTimeWastedCentrifugeInput(crestSample.timeWastedCentrifuge);
      }
      if (crestSample.initialDateDecantation !== undefined && crestSample.initialDateDecantation !== null) {
        crestUpdatePage.setInitialDateDecantationInput(crestSample.initialDateDecantation);
      }
      if (crestSample.finalDateDecantation !== undefined && crestSample.finalDateDecantation !== null) {
        crestUpdatePage.setFinalDateDecantationInput(crestSample.finalDateDecantation);
      }
      if (crestSample.producedHoneyQuantity !== undefined && crestSample.producedHoneyQuantity !== null) {
        crestUpdatePage.setProducedHoneyQuantityInput(crestSample.producedHoneyQuantity);
      }
      if (crestSample.state !== undefined && crestSample.state !== null) {
        crestUpdatePage.setStateInput(crestSample.state);
      }
      crestUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        crest = body;
      });

      crestComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
