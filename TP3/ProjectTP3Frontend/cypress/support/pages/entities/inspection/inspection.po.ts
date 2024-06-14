import { EntityComponentsPage, EntityDetailPage, EntityUpdatePage } from '../../entity.po';

export class InspectionComponentsPage extends EntityComponentsPage {
  pageSelector = 'page-inspection';
}

export class InspectionUpdatePage extends EntityUpdatePage {
  pageSelector = 'page-inspection-update';

  setDateInput(date: string) {
    this.setDate('date', date);
  }

  setNoteInput(note: string) {
    this.setInputValue('note', note);
  }

  setTypeInput(type: string) {
    this.select('type', type);
  }

  setStateInput(state: string) {
    this.select('state', state);
  }
}

export class InspectionDetailPage extends EntityDetailPage {
  pageSelector = 'page-inspection-detail';

  getDateContent() {
    return cy.get('#date-content');
  }

  getNoteContent() {
    return cy.get('#note-content');
  }

  getTypeContent() {
    return cy.get('#type-content');
  }

  getStateContent() {
    return cy.get('#state-content');
  }
}
