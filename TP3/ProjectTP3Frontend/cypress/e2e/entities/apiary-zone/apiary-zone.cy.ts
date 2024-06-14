import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import {
  ApiaryZoneComponentsPage,
  ApiaryZoneDetailPage,
  ApiaryZoneUpdatePage,
} from '../../../support/pages/entities/apiary-zone/apiary-zone.po';
import apiaryZoneSample from './apiary-zone.json';

describe('ApiaryZone entity', () => {
  const COMPONENT_TITLE = 'Apiary Zones';
  const SUBCOMPONENT_TITLE = 'Apiary Zone';

  const apiaryZonePageUrl = '/tabs/entities/apiary-zone';
  const apiaryZoneApiUrl = '/api/apiary-zones';

  const apiaryZoneComponentsPage = new ApiaryZoneComponentsPage();
  const apiaryZoneUpdatePage = new ApiaryZoneUpdatePage();
  const apiaryZoneDetailPage = new ApiaryZoneDetailPage();

  let apiaryZone: any;

  beforeEach(() => {
    apiaryZone = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load ApiaryZones page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      apiaryZoneComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', apiaryZonePageUrl);

      apiaryZoneComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create ApiaryZone page and go back', () => {
      cy.visit(apiaryZonePageUrl);
      apiaryZoneComponentsPage.clickOnCreateButton();

      apiaryZoneUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      apiaryZoneUpdatePage.back();
      cy.url().should('include', apiaryZonePageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: apiaryZoneApiUrl,
        body: apiaryZoneSample,
      }).then(({ body }) => {
        apiaryZone = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${apiaryZoneApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [apiaryZone],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (apiaryZone) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${apiaryZoneApiUrl}/${apiaryZone.id}`,
        }).then(() => {
          apiaryZone = undefined;
        });
      }
    });

    it('should open ApiaryZone view, open ApiaryZone edit and go back', () => {
      cy.visit(apiaryZonePageUrl);
      apiaryZoneComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      apiaryZoneDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      if (apiaryZone.name !== undefined && apiaryZone.name !== null) {
        apiaryZoneDetailPage.getNameContent().contains(apiaryZone.name);
      }
      apiaryZoneDetailPage.edit();

      apiaryZoneUpdatePage.back();
      apiaryZoneDetailPage.back();
      cy.url().should('include', apiaryZonePageUrl);
    });

    it('should open ApiaryZone view, open ApiaryZone edit and save', () => {
      cy.visit(apiaryZonePageUrl);
      apiaryZoneComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      apiaryZoneDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      apiaryZoneDetailPage.edit();

      apiaryZoneUpdatePage.save();
      cy.url().should('include', apiaryZonePageUrl);
    });

    it('should delete ApiaryZone', () => {
      cy.visit(apiaryZonePageUrl);
      apiaryZoneComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      apiaryZoneDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      apiaryZoneComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      apiaryZone = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: apiaryZoneApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (apiaryZone) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${apiaryZoneApiUrl}/${apiaryZone.id}`,
        }).then(() => {
          apiaryZone = undefined;
        });
      }
    });

    it('should create ApiaryZone', () => {
      cy.visit(apiaryZonePageUrl + '/new');

      apiaryZoneUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (apiaryZoneSample.name !== undefined && apiaryZoneSample.name !== null) {
        apiaryZoneUpdatePage.setNameInput(apiaryZoneSample.name);
      }
      if (apiaryZoneSample.controlledZone !== undefined && apiaryZoneSample.controlledZone !== null) {
        apiaryZoneUpdatePage.setControlledZoneInput(apiaryZoneSample.controlledZone);
      }
      if (apiaryZoneSample.state !== undefined && apiaryZoneSample.state !== null) {
        apiaryZoneUpdatePage.setStateInput(apiaryZoneSample.state);
      }
      apiaryZoneUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        apiaryZone = body;
      });

      apiaryZoneComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
