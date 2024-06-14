import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class UnfoldingComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-unfolding';
}

export class UnfoldingUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-unfolding-update';

  setObservationsInput(observations: string) {
    this.setInputValue('observations', observations);
  }

  setDateInput(date: string) {
    this.setDateTime('date', date);
  }
}

export class UnfoldingDetailPage extends EntityDetailPage {
  pageSelector = 'page-unfolding-detail';

  getObservationsContent() {
    return cy.get('#observations-content');
  }

  getDateContent() {
    return cy.get('#date-content');
  }
}
