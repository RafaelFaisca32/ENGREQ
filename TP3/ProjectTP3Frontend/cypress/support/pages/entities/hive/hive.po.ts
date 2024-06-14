import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class HiveComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-hive';
}

export class HiveUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-hive-update';

  setCodeInput(code: string) {
    this.setInputValue('code', code);
  }
}

export class HiveDetailPage extends EntityDetailPage {
  pageSelector = 'page-hive-detail';

  getCodeContent() {
    return cy.get('#code-content');
  }
}
