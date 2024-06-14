import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import {
  ApiaryInformationComponentsPage,
  ApiaryInformationDetailPage,
  ApiaryInformationUpdatePage,
} from '../../../support/pages/entities/apiary-information/apiary-information.po';
import apiaryInformationSample from './apiary-information.json';

describe('ApiaryInformation entity', () => {
  const COMPONENT_TITLE = 'Apiary Informations';
  const SUBCOMPONENT_TITLE = 'Apiary Information';

  const apiaryInformationPageUrl = '/tabs/entities/apiary-information';
  const apiaryInformationApiUrl = '/api/apiary-informations';

  const apiaryInformationComponentsPage = new ApiaryInformationComponentsPage();
  const apiaryInformationUpdatePage = new ApiaryInformationUpdatePage();
  const apiaryInformationDetailPage = new ApiaryInformationDetailPage();

  let apiaryInformation: any;

  beforeEach(() => {
    apiaryInformation = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load ApiaryInformations page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      apiaryInformationComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', apiaryInformationPageUrl);

      apiaryInformationComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create ApiaryInformation page and go back', () => {
      cy.visit(apiaryInformationPageUrl);
      apiaryInformationComponentsPage.clickOnCreateButton();

      apiaryInformationUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      apiaryInformationUpdatePage.back();
      cy.url().should('include', apiaryInformationPageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: apiaryInformationApiUrl,
        body: apiaryInformationSample,
      }).then(({ body }) => {
        apiaryInformation = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${apiaryInformationApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [apiaryInformation],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (apiaryInformation) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${apiaryInformationApiUrl}/${apiaryInformation.id}`,
        }).then(() => {
          apiaryInformation = undefined;
        });
      }
    });

    it('should open ApiaryInformation view, open ApiaryInformation edit and go back', () => {
      cy.visit(apiaryInformationPageUrl);
      apiaryInformationComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      apiaryInformationDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      if (apiaryInformation.zoneNumber !== undefined && apiaryInformation.zoneNumber !== null) {
        apiaryInformationDetailPage.getZoneNumberContent().contains(apiaryInformation.zoneNumber);
      }
      if (apiaryInformation.zoneName !== undefined && apiaryInformation.zoneName !== null) {
        apiaryInformationDetailPage.getZoneNameContent().contains(apiaryInformation.zoneName);
      }
      if (apiaryInformation.numberHives !== undefined && apiaryInformation.numberHives !== null) {
        apiaryInformationDetailPage.getNumberHivesContent().contains(apiaryInformation.numberHives);
      }
      if (apiaryInformation.coordX !== undefined && apiaryInformation.coordX !== null) {
        apiaryInformationDetailPage.getCoordXContent().contains(apiaryInformation.coordX);
      }
      if (apiaryInformation.coordY !== undefined && apiaryInformation.coordY !== null) {
        apiaryInformationDetailPage.getCoordYContent().contains(apiaryInformation.coordY);
      }
      if (apiaryInformation.coordZ !== undefined && apiaryInformation.coordZ !== null) {
        apiaryInformationDetailPage.getCoordZContent().contains(apiaryInformation.coordZ);
      }
      if (apiaryInformation.numberFrames !== undefined && apiaryInformation.numberFrames !== null) {
        apiaryInformationDetailPage.getNumberFramesContent().contains(apiaryInformation.numberFrames);
      }
      apiaryInformationDetailPage.edit();

      apiaryInformationUpdatePage.back();
      apiaryInformationDetailPage.back();
      cy.url().should('include', apiaryInformationPageUrl);
    });

    it('should open ApiaryInformation view, open ApiaryInformation edit and save', () => {
      cy.visit(apiaryInformationPageUrl);
      apiaryInformationComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      apiaryInformationDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      apiaryInformationDetailPage.edit();

      apiaryInformationUpdatePage.save();
      cy.url().should('include', apiaryInformationPageUrl);
    });

    it('should delete ApiaryInformation', () => {
      cy.visit(apiaryInformationPageUrl);
      apiaryInformationComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      apiaryInformationDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      apiaryInformationComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      apiaryInformation = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: apiaryInformationApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (apiaryInformation) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${apiaryInformationApiUrl}/${apiaryInformation.id}`,
        }).then(() => {
          apiaryInformation = undefined;
        });
      }
    });

    it('should create ApiaryInformation', () => {
      cy.visit(apiaryInformationPageUrl + '/new');

      apiaryInformationUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (apiaryInformationSample.zoneNumber !== undefined && apiaryInformationSample.zoneNumber !== null) {
        apiaryInformationUpdatePage.setZoneNumberInput(apiaryInformationSample.zoneNumber);
      }
      if (apiaryInformationSample.zoneName !== undefined && apiaryInformationSample.zoneName !== null) {
        apiaryInformationUpdatePage.setZoneNameInput(apiaryInformationSample.zoneName);
      }
      if (apiaryInformationSample.numberHives !== undefined && apiaryInformationSample.numberHives !== null) {
        apiaryInformationUpdatePage.setNumberHivesInput(apiaryInformationSample.numberHives);
      }
      if (apiaryInformationSample.intensive !== undefined && apiaryInformationSample.intensive !== null) {
        apiaryInformationUpdatePage.setIntensiveInput(apiaryInformationSample.intensive);
      }
      if (apiaryInformationSample.transhumance !== undefined && apiaryInformationSample.transhumance !== null) {
        apiaryInformationUpdatePage.setTranshumanceInput(apiaryInformationSample.transhumance);
      }
      if (apiaryInformationSample.coordX !== undefined && apiaryInformationSample.coordX !== null) {
        apiaryInformationUpdatePage.setCoordXInput(apiaryInformationSample.coordX);
      }
      if (apiaryInformationSample.coordY !== undefined && apiaryInformationSample.coordY !== null) {
        apiaryInformationUpdatePage.setCoordYInput(apiaryInformationSample.coordY);
      }
      if (apiaryInformationSample.coordZ !== undefined && apiaryInformationSample.coordZ !== null) {
        apiaryInformationUpdatePage.setCoordZInput(apiaryInformationSample.coordZ);
      }
      if (apiaryInformationSample.numberFrames !== undefined && apiaryInformationSample.numberFrames !== null) {
        apiaryInformationUpdatePage.setNumberFramesInput(apiaryInformationSample.numberFrames);
      }
      apiaryInformationUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        apiaryInformation = body;
      });

      apiaryInformationComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
