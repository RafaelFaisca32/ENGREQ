import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class FrameComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-frame';
}

export class FrameUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-frame-update';

  setCodeInput(code: string) {
    this.setInputValue('code', code);
  }
}

export class FrameDetailPage extends EntityDetailPage {
  pageSelector = 'page-frame-detail';

  getCodeContent() {
    return cy.get('#code-content');
  }
}
