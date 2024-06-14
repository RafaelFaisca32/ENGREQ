import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import {
  InspectionComponentsPage,
  InspectionDetailPage,
  InspectionUpdatePage,
} from '../../../support/pages/entities/inspection/inspection.po';
import inspectionSample from './inspection.json';

describe('Inspection entity', () => {
  const COMPONENT_TITLE = 'Inspections';
  const SUBCOMPONENT_TITLE = 'Inspection';

  const inspectionPageUrl = '/tabs/entities/inspection';
  const inspectionApiUrl = '/api/inspections';

  const inspectionComponentsPage = new InspectionComponentsPage();
  const inspectionUpdatePage = new InspectionUpdatePage();
  const inspectionDetailPage = new InspectionDetailPage();

  let inspection: any;

  beforeEach(() => {
    inspection = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load Inspections page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      inspectionComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', inspectionPageUrl);

      inspectionComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create Inspection page and go back', () => {
      cy.visit(inspectionPageUrl);
      inspectionComponentsPage.clickOnCreateButton();

      inspectionUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      inspectionUpdatePage.back();
      cy.url().should('include', inspectionPageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: inspectionApiUrl,
        body: inspectionSample,
      }).then(({ body }) => {
        inspection = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${inspectionApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [inspection],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (inspection) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${inspectionApiUrl}/${inspection.id}`,
        }).then(() => {
          inspection = undefined;
        });
      }
    });

    it('should open Inspection view, open Inspection edit and go back', () => {
      cy.visit(inspectionPageUrl);
      inspectionComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      inspectionDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      if (inspection.note !== undefined && inspection.note !== null) {
        inspectionDetailPage.getNoteContent().contains(inspection.note);
      }
      inspectionDetailPage.edit();

      inspectionUpdatePage.back();
      inspectionDetailPage.back();
      cy.url().should('include', inspectionPageUrl);
    });

    it('should open Inspection view, open Inspection edit and save', () => {
      cy.visit(inspectionPageUrl);
      inspectionComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      inspectionDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      inspectionDetailPage.edit();

      inspectionUpdatePage.save();
      cy.url().should('include', inspectionPageUrl);
    });

    it('should delete Inspection', () => {
      cy.visit(inspectionPageUrl);
      inspectionComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      inspectionDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      inspectionComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      inspection = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: inspectionApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (inspection) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${inspectionApiUrl}/${inspection.id}`,
        }).then(() => {
          inspection = undefined;
        });
      }
    });

    it('should create Inspection', () => {
      cy.visit(inspectionPageUrl + '/new');

      inspectionUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (inspectionSample.date !== undefined && inspectionSample.date !== null) {
        inspectionUpdatePage.setDateInput(inspectionSample.date);
      }
      if (inspectionSample.note !== undefined && inspectionSample.note !== null) {
        inspectionUpdatePage.setNoteInput(inspectionSample.note);
      }
      inspectionUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        inspection = body;
      });

      inspectionComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
