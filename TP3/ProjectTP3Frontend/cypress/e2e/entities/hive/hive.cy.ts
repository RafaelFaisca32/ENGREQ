import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import { HiveComponentsPage, HiveDetailPage, HiveUpdatePage } from '../../../support/pages/entities/hive/hive.po';
import hiveSample from './hive.json';

describe('Hive entity', () => {
  const COMPONENT_TITLE = 'Hives';
  const SUBCOMPONENT_TITLE = 'Hive';

  const hivePageUrl = '/tabs/entities/hive';
  const hiveApiUrl = '/api/hives';

  const hiveComponentsPage = new HiveComponentsPage();
  const hiveUpdatePage = new HiveUpdatePage();
  const hiveDetailPage = new HiveDetailPage();

  let hive: any;

  beforeEach(() => {
    hive = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load Hives page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      hiveComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', hivePageUrl);

      hiveComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create Hive page and go back', () => {
      cy.visit(hivePageUrl);
      hiveComponentsPage.clickOnCreateButton();

      hiveUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      hiveUpdatePage.back();
      cy.url().should('include', hivePageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: hiveApiUrl,
        body: hiveSample,
      }).then(({ body }) => {
        hive = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${hiveApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [hive],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (hive) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${hiveApiUrl}/${hive.id}`,
        }).then(() => {
          hive = undefined;
        });
      }
    });

    it('should open Hive view, open Hive edit and go back', () => {
      cy.visit(hivePageUrl);
      hiveComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      hiveDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      if (hive.code !== undefined && hive.code !== null) {
        hiveDetailPage.getCodeContent().contains(hive.code);
      }
      hiveDetailPage.edit();

      hiveUpdatePage.back();
      hiveDetailPage.back();
      cy.url().should('include', hivePageUrl);
    });

    it('should open Hive view, open Hive edit and save', () => {
      cy.visit(hivePageUrl);
      hiveComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      hiveDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      hiveDetailPage.edit();

      hiveUpdatePage.save();
      cy.url().should('include', hivePageUrl);
    });

    it('should delete Hive', () => {
      cy.visit(hivePageUrl);
      hiveComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      hiveDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      hiveComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      hive = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: hiveApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (hive) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${hiveApiUrl}/${hive.id}`,
        }).then(() => {
          hive = undefined;
        });
      }
    });

    it('should create Hive', () => {
      cy.visit(hivePageUrl + '/new');

      hiveUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (hiveSample.code !== undefined && hiveSample.code !== null) {
        hiveUpdatePage.setCodeInput(hiveSample.code);
      }
      hiveUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        hive = body;
      });

      hiveComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
