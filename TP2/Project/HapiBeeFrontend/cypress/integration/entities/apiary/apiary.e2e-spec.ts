import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import { ApiaryComponentsPage, ApiaryDetailPage, ApiaryUpdatePage } from '../../../support/pages/entities/apiary/apiary.po';
import apiarySample from './apiary.json';

describe('Apiary entity', () => {
  const COMPONENT_TITLE = 'Apiaries';
  const SUBCOMPONENT_TITLE = 'Apiary';

  const apiaryPageUrl = '/tabs/entities/apiary';
  const apiaryApiUrl = '/api/apiaries';

  const apiaryComponentsPage = new ApiaryComponentsPage();
  const apiaryUpdatePage = new ApiaryUpdatePage();
  const apiaryDetailPage = new ApiaryDetailPage();

  let apiary: any;

  beforeEach(() => {
    apiary = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load Apiaries page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      apiaryComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', apiaryPageUrl);

      apiaryComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create Apiary page and go back', () => {
      cy.visit(apiaryPageUrl);
      apiaryComponentsPage.clickOnCreateButton();

      apiaryUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      apiaryUpdatePage.back();
      cy.url().should('include', apiaryPageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: apiaryApiUrl,
        body: apiarySample,
      }).then(({ body }) => {
        apiary = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${apiaryApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [apiary],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (apiary) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${apiaryApiUrl}/${apiary.id}`,
        }).then(() => {
          apiary = undefined;
        });
      }
    });

    it('should open Apiary view, open Apiary edit and go back', () => {
      cy.visit(apiaryPageUrl);
      apiaryComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      apiaryDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      if (apiary.name !== undefined && apiary.name !== null) {
        apiaryDetailPage.getNameContent().contains(apiary.name);
      }
      if (apiary.number !== undefined && apiary.number !== null) {
        apiaryDetailPage.getNumberContent().contains(apiary.number);
      }
      apiaryDetailPage.edit();

      apiaryUpdatePage.back();
      apiaryDetailPage.back();
      cy.url().should('include', apiaryPageUrl);
    });

    it('should open Apiary view, open Apiary edit and save', () => {
      cy.visit(apiaryPageUrl);
      apiaryComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      apiaryDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      apiaryDetailPage.edit();

      apiaryUpdatePage.save();
      cy.url().should('include', apiaryPageUrl);
    });

    it('should delete Apiary', () => {
      cy.visit(apiaryPageUrl);
      apiaryComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      apiaryDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      apiaryComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      apiary = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: apiaryApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (apiary) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${apiaryApiUrl}/${apiary.id}`,
        }).then(() => {
          apiary = undefined;
        });
      }
    });

    it('should create Apiary', () => {
      cy.visit(apiaryPageUrl + '/new');

      apiaryUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (apiarySample.name !== undefined && apiarySample.name !== null) {
        apiaryUpdatePage.setNameInput(apiarySample.name);
      }
      if (apiarySample.state !== undefined && apiarySample.state !== null) {
        apiaryUpdatePage.setStateInput(apiarySample.state);
      }
      if (apiarySample.number !== undefined && apiarySample.number !== null) {
        apiaryUpdatePage.setNumberInput(apiarySample.number);
      }
      if (apiarySample.intensive !== undefined && apiarySample.intensive !== null) {
        apiaryUpdatePage.setIntensiveInput(apiarySample.intensive);
      }
      if (apiarySample.transhumance !== undefined && apiarySample.transhumance !== null) {
        apiaryUpdatePage.setTranshumanceInput(apiarySample.transhumance);
      }
      apiaryUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        apiary = body;
      });

      apiaryComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
