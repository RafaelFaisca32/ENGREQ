import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class DiseaseComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-disease';
}

export class DiseaseUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-disease-update';

  setNameInput(name: string) {
    this.setInputValue('name', name);
  }

  setDescriptionInput(description: string) {
    this.setInputValue('description', description);
  }
}

export class DiseaseDetailPage extends EntityDetailPage {
  pageSelector = 'page-disease-detail';

  getNameContent() {
    return cy.get('#name-content');
  }

  getDescriptionContent() {
    return cy.get('#description-content');
  }
}
