import { USER_USERNAME, USER_PASSWORD } from '../../../support/config';
import { FrameComponentsPage, FrameDetailPage, FrameUpdatePage } from '../../../support/pages/entities/frame/frame.po';
import frameSample from './frame.json';

describe('Frame entity', () => {
  const COMPONENT_TITLE = 'Frames';
  const SUBCOMPONENT_TITLE = 'Frame';

  const framePageUrl = '/tabs/entities/frame';
  const frameApiUrl = '/api/frames';

  const frameComponentsPage = new FrameComponentsPage();
  const frameUpdatePage = new FrameUpdatePage();
  const frameDetailPage = new FrameDetailPage();

  let frame: any;

  beforeEach(() => {
    frame = undefined;
    cy.login(USER_USERNAME, USER_PASSWORD);
  });

  describe('navigation test', () => {
    it('should load Frames page using menu and go back', () => {
      cy.visit('/tabs/home');
      // go to entity component page
      cy.get('ion-tab-button[tab="entities"]').click();
      cy.get('ion-item h2').contains(SUBCOMPONENT_TITLE).first().click();

      frameComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE).should('be.visible');
      cy.url().should('include', framePageUrl);

      frameComponentsPage.back();
      cy.url().should('include', '/tabs/entities');
    });

    it('should load create Frame page and go back', () => {
      cy.visit(framePageUrl);
      frameComponentsPage.clickOnCreateButton();

      frameUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);

      frameUpdatePage.back();
      cy.url().should('include', framePageUrl);
    });
  });

  describe('navigation test with items', () => {
    beforeEach(() => {
      cy.authenticatedRequest({
        method: 'POST',
        url: frameApiUrl,
        body: frameSample,
      }).then(({ body }) => {
        frame = body;

        cy.intercept(
          {
            method: 'GET',
            url: `${frameApiUrl}+(?*|)`,
            times: 1,
          },
          {
            statusCode: 200,
            body: [frame],
          }
        ).as('entitiesRequestInternal');
      });
    });

    afterEach(() => {
      if (frame) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${frameApiUrl}/${frame.id}`,
        }).then(() => {
          frame = undefined;
        });
      }
    });

    it('should open Frame view, open Frame edit and go back', () => {
      cy.visit(framePageUrl);
      frameComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      frameDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      if (frame.code !== undefined && frame.code !== null) {
        frameDetailPage.getCodeContent().contains(frame.code);
      }
      frameDetailPage.edit();

      frameUpdatePage.back();
      frameDetailPage.back();
      cy.url().should('include', framePageUrl);
    });

    it('should open Frame view, open Frame edit and save', () => {
      cy.visit(framePageUrl);
      frameComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      frameDetailPage.getPageTitle().contains(SUBCOMPONENT_TITLE).should('be.visible');
      frameDetailPage.edit();

      frameUpdatePage.save();
      cy.url().should('include', framePageUrl);
    });

    it('should delete Frame', () => {
      cy.visit(framePageUrl);
      frameComponentsPage.getPageTitle().should('be.visible');

      cy.wait('@entitiesRequestInternal');
      cy.get('ion-item').last().click();

      frameDetailPage.delete();
      cy.get('ion-alert button:not(.alert-button-role-cancel)').click();

      frameComponentsPage.getPageTitle().should('have.text', COMPONENT_TITLE);
      frame = undefined;
    });
  });

  describe('creation test', () => {
    beforeEach(() => {
      cy.intercept({
        method: 'POST',
        url: frameApiUrl,
        times: 1,
      }).as('entitiesPost');
    });

    afterEach(() => {
      if (frame) {
        cy.authenticatedRequest({
          method: 'DELETE',
          url: `${frameApiUrl}/${frame.id}`,
        }).then(() => {
          frame = undefined;
        });
      }
    });

    it('should create Frame', () => {
      cy.visit(framePageUrl + '/new');

      frameUpdatePage.getPageTitle().should('have.text', SUBCOMPONENT_TITLE);
      if (frameSample.code !== undefined && frameSample.code !== null) {
        frameUpdatePage.setCodeInput(frameSample.code);
      }
      frameUpdatePage.save();

      cy.wait('@entitiesPost').then(({ response }) => {
        const { body } = response;
        frame = body;
      });

      frameComponentsPage.getPageTitle().contains(COMPONENT_TITLE);
    });
  });
});
