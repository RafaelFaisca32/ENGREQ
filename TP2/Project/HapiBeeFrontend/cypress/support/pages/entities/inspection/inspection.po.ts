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
}

export class InspectionDetailPage extends EntityDetailPage {
  pageSelector = 'page-inspection-detail';

  getDateContent() {
    return cy.get('#date-content');
  }

  getNoteContent() {
    return cy.get('#note-content');
  }
}
