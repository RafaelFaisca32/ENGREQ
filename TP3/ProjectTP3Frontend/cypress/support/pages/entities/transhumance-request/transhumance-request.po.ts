import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class TranshumanceRequestComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-transhumance-request';
}

export class TranshumanceRequestUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-transhumance-request-update';

  setRequestDateInput(requestDate: string) {
    this.setDateTime('requestDate', requestDate);
  }

  setStateInput(state: string) {
    this.select('state', state);
  }
}

export class TranshumanceRequestDetailPage extends EntityDetailPage {
  pageSelector = 'page-transhumance-request-detail';

  getRequestDateContent() {
    return cy.get('#requestDate-content');
  }

  getStateContent() {
    return cy.get('#state-content');
  }
}
