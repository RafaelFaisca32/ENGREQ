import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import {
  TranshumanceRequestComponentsPage,
  TranshumanceRequestDetailPage,
  TranshumanceRequestUpdatePage,
} from '../../../support/pages/entities/transhumance-request/transhumance-request.po';
import transhumanceRequestSample from './transhumance-request.json';

describe('TranshumanceRequest entity', () => {
  const COMPONENT_TITLE = 'Transhumance Requests';
  const SUBCOMPONENT_TITLE = 'Transhumance Request';

  const transhumanceRequestPageUrl = '/tabs/entities/transhumance-request';
  const transhumanceRequestApiUrl = '/api/transhumance-requests';

  const transhumanceRequestComponentsPage = new TranshumanceRequestComponentsPage();
  const transhumanceRequestUpdatePage = new TranshumanceRequestUpdatePage();
  const transhumanceRequestDetailPage = new TranshumanceRequestDetailPage();

  let transhumanceRequest: any;

  beforeEach(() => {
    transhumanceRequest = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load TranshumanceRequests page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      transhumanceRequestComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', transhumanceRequestPageUrl);

      transhumanceRequestComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create TranshumanceRequest page and go back', () => {
      cy.visit(transhumanceRequestPageUrl);
      transhumanceRequestComponentsPage.clickOnCreateButton();

      transhumanceRequestUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      transhumanceRequestUpdatePage.back();
      cy.url().should('include', transhumanceRequestPageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: transhumanceRequestApiUrl,
        body: transhumanceRequestSample,
      }).then(({ body }) => {
        transhumanceRequest = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${transhumanceRequestApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [transhumanceRequest],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (transhumanceRequest) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${transhumanceRequestApiUrl}/${transhumanceRequest.id}`,
        }).then(() => {
          transhumanceRequest = undefined;
        });
      }
    });

    it('should open TranshumanceRequest view, open TranshumanceRequest edit and go back', () => {
      cy.visit(transhumanceRequestPageUrl);
      transhumanceRequestComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      transhumanceRequestDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      transhumanceRequestDetailPage.edit();

      transhumanceRequestUpdatePage.back();
      transhumanceRequestDetailPage.back();
      cy.url().should('include', transhumanceRequestPageUrl);
    });

    it('should open TranshumanceRequest view, open TranshumanceRequest edit and save', () => {
      cy.visit(transhumanceRequestPageUrl);
      transhumanceRequestComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      transhumanceRequestDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      transhumanceRequestDetailPage.edit();

      transhumanceRequestUpdatePage.save();
      cy.url().should('include', transhumanceRequestPageUrl);
    });

    it('should delete TranshumanceRequest', () => {
      cy.visit(transhumanceRequestPageUrl);
      transhumanceRequestComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      transhumanceRequestDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      transhumanceRequestComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      transhumanceRequest = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: transhumanceRequestApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (transhumanceRequest) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${transhumanceRequestApiUrl}/${transhumanceRequest.id}`,
        }).then(() => {
          transhumanceRequest = undefined;
        });
      }
    });

    it('should create TranshumanceRequest', () => {
      cy.visit(transhumanceRequestPageUrl + '/new');

      transhumanceRequestUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (transhumanceRequestSample.requestDate !== undefined && transhumanceRequestSample.requestDate !== null) {
        transhumanceRequestUpdatePage.setRequestDateInput(transhumanceRequestSample.requestDate);
      }
      if (transhumanceRequestSample.state !== undefined && transhumanceRequestSample.state !== null) {
        transhumanceRequestUpdatePage.setStateInput(transhumanceRequestSample.state);
      }
      transhumanceRequestUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        transhumanceRequest = body;
      });

      transhumanceRequestComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
