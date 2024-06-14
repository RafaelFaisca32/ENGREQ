import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import { DiseaseComponentsPage, DiseaseDetailPage, DiseaseUpdatePage } from '../../../support/pages/entities/disease/disease.po';
import diseaseSample from './disease.json';

describe('Disease entity', () => {
  const COMPONENT_TITLE = 'Diseases';
  const SUBCOMPONENT_TITLE = 'Disease';

  const diseasePageUrl = '/tabs/entities/disease';
  const diseaseApiUrl = '/api/diseases';

  const diseaseComponentsPage = new DiseaseComponentsPage();
  const diseaseUpdatePage = new DiseaseUpdatePage();
  const diseaseDetailPage = new DiseaseDetailPage();

  let disease: any;

  beforeEach(() => {
    disease = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load Diseases page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      diseaseComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', diseasePageUrl);

      diseaseComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create Disease page and go back', () => {
      cy.visit(diseasePageUrl);
      diseaseComponentsPage.clickOnCreateButton();

      diseaseUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      diseaseUpdatePage.back();
      cy.url().should('include', diseasePageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: diseaseApiUrl,
        body: diseaseSample,
      }).then(({ body }) => {
        disease = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${diseaseApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [disease],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (disease) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${diseaseApiUrl}/${disease.id}`,
        }).then(() => {
          disease = undefined;
        });
      }
    });

    it('should open Disease view, open Disease edit and go back', () => {
      cy.visit(diseasePageUrl);
      diseaseComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      diseaseDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      if (disease.name !== undefined && disease.name !== null) {
        diseaseDetailPage.getNameContent().contains(disease.name);
      }
      if (disease.description !== undefined && disease.description !== null) {
        diseaseDetailPage.getDescriptionContent().contains(disease.description);
      }
      diseaseDetailPage.edit();

      diseaseUpdatePage.back();
      diseaseDetailPage.back();
      cy.url().should('include', diseasePageUrl);
    });

    it('should open Disease view, open Disease edit and save', () => {
      cy.visit(diseasePageUrl);
      diseaseComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      diseaseDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      diseaseDetailPage.edit();

      diseaseUpdatePage.save();
      cy.url().should('include', diseasePageUrl);
    });

    it('should delete Disease', () => {
      cy.visit(diseasePageUrl);
      diseaseComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      diseaseDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      diseaseComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      disease = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: diseaseApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (disease) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${diseaseApiUrl}/${disease.id}`,
        }).then(() => {
          disease = undefined;
        });
      }
    });

    it('should create Disease', () => {
      cy.visit(diseasePageUrl + '/new');

      diseaseUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (diseaseSample.name !== undefined && diseaseSample.name !== null) {
        diseaseUpdatePage.setNameInput(diseaseSample.name);
      }
      if (diseaseSample.description !== undefined && diseaseSample.description !== null) {
        diseaseUpdatePage.setDescriptionInput(diseaseSample.description);
      }
      diseaseUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        disease = body;
      });

      diseaseComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
