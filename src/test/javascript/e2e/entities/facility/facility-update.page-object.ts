import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class FacilityUpdatePage {
  pageTitle: ElementFinder = element(by.id('collectorApp.facility.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  descriptionInput: ElementFinder = element(by.css('input#facility-description'));
  userTypeSelect: ElementFinder = element(by.css('select#facility-userType'));
  rentCycleSelect: ElementFinder = element(by.css('select#facility-rentCycle'));
  locationInput: ElementFinder = element(by.css('input#facility-location'));
  refNumberInput: ElementFinder = element(by.css('input#facility-refNumber'));
  sizeInput: ElementFinder = element(by.css('input#facility-size'));
  dateRegisteredInput: ElementFinder = element(by.css('input#facility-dateRegistered'));
  statusSelect: ElementFinder = element(by.css('select#facility-status'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return this.descriptionInput.getAttribute('value');
  }

  async setUserTypeSelect(userType) {
    await this.userTypeSelect.sendKeys(userType);
  }

  async getUserTypeSelect() {
    return this.userTypeSelect.element(by.css('option:checked')).getText();
  }

  async userTypeSelectLastOption() {
    await this.userTypeSelect.all(by.tagName('option')).last().click();
  }
  async setRentCycleSelect(rentCycle) {
    await this.rentCycleSelect.sendKeys(rentCycle);
  }

  async getRentCycleSelect() {
    return this.rentCycleSelect.element(by.css('option:checked')).getText();
  }

  async rentCycleSelectLastOption() {
    await this.rentCycleSelect.all(by.tagName('option')).last().click();
  }
  async setLocationInput(location) {
    await this.locationInput.sendKeys(location);
  }

  async getLocationInput() {
    return this.locationInput.getAttribute('value');
  }

  async setRefNumberInput(refNumber) {
    await this.refNumberInput.sendKeys(refNumber);
  }

  async getRefNumberInput() {
    return this.refNumberInput.getAttribute('value');
  }

  async setSizeInput(size) {
    await this.sizeInput.sendKeys(size);
  }

  async getSizeInput() {
    return this.sizeInput.getAttribute('value');
  }

  async setDateRegisteredInput(dateRegistered) {
    await this.dateRegisteredInput.sendKeys(dateRegistered);
  }

  async getDateRegisteredInput() {
    return this.dateRegisteredInput.getAttribute('value');
  }

  async setStatusSelect(status) {
    await this.statusSelect.sendKeys(status);
  }

  async getStatusSelect() {
    return this.statusSelect.element(by.css('option:checked')).getText();
  }

  async statusSelectLastOption() {
    await this.statusSelect.all(by.tagName('option')).last().click();
  }
  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }

  async enterData() {
    await waitUntilDisplayed(this.saveButton);
    await this.setDescriptionInput('description');
    await waitUntilDisplayed(this.saveButton);
    await this.userTypeSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.rentCycleSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.setLocationInput('location');
    await waitUntilDisplayed(this.saveButton);
    await this.setRefNumberInput('refNumber');
    await waitUntilDisplayed(this.saveButton);
    await this.setSizeInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setDateRegisteredInput('01-01-2001');
    await waitUntilDisplayed(this.saveButton);
    await this.statusSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
