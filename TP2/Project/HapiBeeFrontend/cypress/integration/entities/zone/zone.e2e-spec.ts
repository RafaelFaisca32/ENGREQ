import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import { ZoneComponentsPage, ZoneDetailPage, ZoneUpdatePage } from '../../../support/pages/entities/zone/zone.po';
import zoneSample from './zone.json';

describe('Zone entity', () => {
  const COMPONENT_TITLE = 'Zones';
  const SUBCOMPONENT_TITLE = 'Zone';

  const zonePageUrl = '/tabs/entities/zone';
  const zoneApiUrl = '/api/zones';

  const zoneComponentsPage = new ZoneComponentsPage();
  const zoneUpdatePage = new ZoneUpdatePage();
  const zoneDetailPage = new ZoneDetailPage();

  let zone: any;

  beforeEach(() => {
    zone = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load Zones page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      zoneComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', zonePageUrl);

      zoneComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create Zone page and go back', () => {
      cy.visit(zonePageUrl);
      zoneComponentsPage.clickOnCreateButton();

      zoneUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      zoneUpdatePage.back();
      cy.url().should('include', zonePageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: zoneApiUrl,
        body: zoneSample,
      }).then(({ body }) => {
        zone = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${zoneApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [zone],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (zone) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${zoneApiUrl}/${zone.id}`,
        }).then(() => {
          zone = undefined;
        });
      }
    });

    it('should open Zone view, open Zone edit and go back', () => {
      cy.visit(zonePageUrl);
      zoneComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      zoneDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      if (zone.name !== undefined && zone.name !== null) {
        zoneDetailPage.getNameContent().contains(zone.name);
      }
      if (zone.number !== undefined && zone.number !== null) {
        zoneDetailPage.getNumberContent().contains(zone.number);
      }
      if (zone.coordX !== undefined && zone.coordX !== null) {
        zoneDetailPage.getCoordXContent().contains(zone.coordX);
      }
      if (zone.coordY !== undefined && zone.coordY !== null) {
        zoneDetailPage.getCoordYContent().contains(zone.coordY);
      }
      if (zone.coordZ !== undefined && zone.coordZ !== null) {
        zoneDetailPage.getCoordZContent().contains(zone.coordZ);
      }
      zoneDetailPage.edit();

      zoneUpdatePage.back();
      zoneDetailPage.back();
      cy.url().should('include', zonePageUrl);
    });

    it('should open Zone view, open Zone edit and save', () => {
      cy.visit(zonePageUrl);
      zoneComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      zoneDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      zoneDetailPage.edit();

      zoneUpdatePage.save();
      cy.url().should('include', zonePageUrl);
    });

    it('should delete Zone', () => {
      cy.visit(zonePageUrl);
      zoneComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      zoneDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      zoneComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      zone = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: zoneApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (zone) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${zoneApiUrl}/${zone.id}`,
        }).then(() => {
          zone = undefined;
        });
      }
    });

    it('should create Zone', () => {
      cy.visit(zonePageUrl + '/new');

      zoneUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (zoneSample.name !== undefined && zoneSample.name !== null) {
        zoneUpdatePage.setNameInput(zoneSample.name);
      }
      if (zoneSample.number !== undefined && zoneSample.number !== null) {
        zoneUpdatePage.setNumberInput(zoneSample.number);
      }
      if (zoneSample.coordX !== undefined && zoneSample.coordX !== null) {
        zoneUpdatePage.setCoordXInput(zoneSample.coordX);
      }
      if (zoneSample.coordY !== undefined && zoneSample.coordY !== null) {
        zoneUpdatePage.setCoordYInput(zoneSample.coordY);
      }
      if (zoneSample.coordZ !== undefined && zoneSample.coordZ !== null) {
        zoneUpdatePage.setCoordZInput(zoneSample.coordZ);
      }
      if (zoneSample.controlledZone !== undefined && zoneSample.controlledZone !== null) {
        zoneUpdatePage.setControlledZoneInput(zoneSample.controlledZone);
      }
      zoneUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        zone = body;
      });

      zoneComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
