import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import FacilityUpdatePage from './facility-update.page-object';

const expect = chai.expect;
export class FacilityDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('collectorApp.facility.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-facility'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class FacilityComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('facility-heading'));
  noRecords: ElementFinder = element(by.css('#app-view-container .table-responsive div.alert.alert-warning'));
  table: ElementFinder = element(by.css('#app-view-container div.table-responsive > table'));

  records: ElementArrayFinder = this.table.all(by.css('tbody tr'));

  getDetailsButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-info.btn-sm'));
  }

  getEditButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-primary.btn-sm'));
  }

  getDeleteButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-danger.btn-sm'));
  }

  async goToPage(navBarPage: NavBarPage) {
    await navBarPage.getEntityPage('facility');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateFacility() {
    await this.createButton.click();
    return new FacilityUpdatePage();
  }

  async deleteFacility() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const facilityDeleteDialog = new FacilityDeleteDialog();
    await waitUntilDisplayed(facilityDeleteDialog.deleteModal);
    expect(await facilityDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/collectorApp.facility.delete.question/);
    await facilityDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(facilityDeleteDialog.deleteModal);

    expect(await isVisible(facilityDeleteDialog.deleteModal)).to.be.false;
  }
}
