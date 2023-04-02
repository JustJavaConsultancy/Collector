import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class DateIntervalUpdatePage {
  pageTitle: ElementFinder = element(by.id('collectorApp.dateInterval.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  intervalInput: ElementFinder = element(by.css('input#date-interval-interval'));
  measureSelect: ElementFinder = element(by.css('select#date-interval-measure'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setIntervalInput(interval) {
    await this.intervalInput.sendKeys(interval);
  }

  async getIntervalInput() {
    return this.intervalInput.getAttribute('value');
  }

  async setMeasureSelect(measure) {
    await this.measureSelect.sendKeys(measure);
  }

  async getMeasureSelect() {
    return this.measureSelect.element(by.css('option:checked')).getText();
  }

  async measureSelectLastOption() {
    await this.measureSelect.all(by.tagName('option')).last().click();
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
    await this.setIntervalInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.measureSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
