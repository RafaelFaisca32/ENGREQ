import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import { UnfoldingComponentsPage, UnfoldingDetailPage, UnfoldingUpdatePage } from '../../../support/pages/entities/unfolding/unfolding.po';
import unfoldingSample from './unfolding.json';

describe('Unfolding entity', () => {
  const COMPONENT_TITLE = 'Unfoldings';
  const SUBCOMPONENT_TITLE = 'Unfolding';

  const unfoldingPageUrl = '/tabs/entities/unfolding';
  const unfoldingApiUrl = '/api/unfoldings';

  const unfoldingComponentsPage = new UnfoldingComponentsPage();
  const unfoldingUpdatePage = new UnfoldingUpdatePage();
  const unfoldingDetailPage = new UnfoldingDetailPage();

  let unfolding: any;

  beforeEach(() => {
    unfolding = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load Unfoldings page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      unfoldingComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', unfoldingPageUrl);

      unfoldingComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create Unfolding page and go back', () => {
      cy.visit(unfoldingPageUrl);
      unfoldingComponentsPage.clickOnCreateButton();

      unfoldingUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      unfoldingUpdatePage.back();
      cy.url().should('include', unfoldingPageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: unfoldingApiUrl,
        body: unfoldingSample,
      }).then(({ body }) => {
        unfolding = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${unfoldingApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [unfolding],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (unfolding) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${unfoldingApiUrl}/${unfolding.id}`,
        }).then(() => {
          unfolding = undefined;
        });
      }
    });

    it('should open Unfolding view, open Unfolding edit and go back', () => {
      cy.visit(unfoldingPageUrl);
      unfoldingComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      unfoldingDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      if (unfolding.observations !== undefined && unfolding.observations !== null) {
        unfoldingDetailPage.getObservationsContent().contains(unfolding.observations);
      }
      unfoldingDetailPage.edit();

      unfoldingUpdatePage.back();
      unfoldingDetailPage.back();
      cy.url().should('include', unfoldingPageUrl);
    });

    it('should open Unfolding view, open Unfolding edit and save', () => {
      cy.visit(unfoldingPageUrl);
      unfoldingComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      unfoldingDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      unfoldingDetailPage.edit();

      unfoldingUpdatePage.save();
      cy.url().should('include', unfoldingPageUrl);
    });

    it('should delete Unfolding', () => {
      cy.visit(unfoldingPageUrl);
      unfoldingComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      unfoldingDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      unfoldingComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      unfolding = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: unfoldingApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (unfolding) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${unfoldingApiUrl}/${unfolding.id}`,
        }).then(() => {
          unfolding = undefined;
        });
      }
    });

    it('should create Unfolding', () => {
      cy.visit(unfoldingPageUrl + '/new');

      unfoldingUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (unfoldingSample.observations !== undefined && unfoldingSample.observations !== null) {
        unfoldingUpdatePage.setObservationsInput(unfoldingSample.observations);
      }
      if (unfoldingSample.date !== undefined && unfoldingSample.date !== null) {
        unfoldingUpdatePage.setDateInput(unfoldingSample.date);
      }
      if (unfoldingSample.state !== undefined && unfoldingSample.state !== null) {
        unfoldingUpdatePage.setStateInput(unfoldingSample.state);
      }
      unfoldingUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        unfolding = body;
      });

      unfoldingComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
