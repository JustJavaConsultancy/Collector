import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class SubscriberUpdatePage {
  pageTitle: ElementFinder = element(by.id('collectorApp.subscriber.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  lastNameInput: ElementFinder = element(by.css('input#subscriber-lastName'));
  firstNameInput: ElementFinder = element(by.css('input#subscriber-firstName'));
  middleNameInput: ElementFinder = element(by.css('input#subscriber-middleName'));
  typeSelect: ElementFinder = element(by.css('select#subscriber-type'));
  dateOfBirthInput: ElementFinder = element(by.css('input#subscriber-dateOfBirth'));
  phoneNumberInput: ElementFinder = element(by.css('input#subscriber-phoneNumber'));
  idNumberInput: ElementFinder = element(by.css('input#subscriber-idNumber'));
  statusSelect: ElementFinder = element(by.css('select#subscriber-status'));
  addressInput: ElementFinder = element(by.css('input#subscriber-address'));
  emailInput: ElementFinder = element(by.css('input#subscriber-email'));
  dateRegisteredInput: ElementFinder = element(by.css('input#subscriber-dateRegistered'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setLastNameInput(lastName) {
    await this.lastNameInput.sendKeys(lastName);
  }

  async getLastNameInput() {
    return this.lastNameInput.getAttribute('value');
  }

  async setFirstNameInput(firstName) {
    await this.firstNameInput.sendKeys(firstName);
  }

  async getFirstNameInput() {
    return this.firstNameInput.getAttribute('value');
  }

  async setMiddleNameInput(middleName) {
    await this.middleNameInput.sendKeys(middleName);
  }

  async getMiddleNameInput() {
    return this.middleNameInput.getAttribute('value');
  }

  async setTypeSelect(type) {
    await this.typeSelect.sendKeys(type);
  }

  async getTypeSelect() {
    return this.typeSelect.element(by.css('option:checked')).getText();
  }

  async typeSelectLastOption() {
    await this.typeSelect.all(by.tagName('option')).last().click();
  }
  async setDateOfBirthInput(dateOfBirth) {
    await this.dateOfBirthInput.sendKeys(dateOfBirth);
  }

  async getDateOfBirthInput() {
    return this.dateOfBirthInput.getAttribute('value');
  }

  async setPhoneNumberInput(phoneNumber) {
    await this.phoneNumberInput.sendKeys(phoneNumber);
  }

  async getPhoneNumberInput() {
    return this.phoneNumberInput.getAttribute('value');
  }

  async setIdNumberInput(idNumber) {
    await this.idNumberInput.sendKeys(idNumber);
  }

  async getIdNumberInput() {
    return this.idNumberInput.getAttribute('value');
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
  async setAddressInput(address) {
    await this.addressInput.sendKeys(address);
  }

  async getAddressInput() {
    return this.addressInput.getAttribute('value');
  }

  async setEmailInput(email) {
    await this.emailInput.sendKeys(email);
  }

  async getEmailInput() {
    return this.emailInput.getAttribute('value');
  }

  async setDateRegisteredInput(dateRegistered) {
    await this.dateRegisteredInput.sendKeys(dateRegistered);
  }

  async getDateRegisteredInput() {
    return this.dateRegisteredInput.getAttribute('value');
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
    await this.setLastNameInput('lastName');
    await waitUntilDisplayed(this.saveButton);
    await this.setFirstNameInput('firstName');
    await waitUntilDisplayed(this.saveButton);
    await this.setMiddleNameInput('middleName');
    await waitUntilDisplayed(this.saveButton);
    await this.typeSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.setDateOfBirthInput('01-01-2001');
    await waitUntilDisplayed(this.saveButton);
    await this.setPhoneNumberInput('phoneNumber');
    await waitUntilDisplayed(this.saveButton);
    await this.setIdNumberInput('idNumber');
    await waitUntilDisplayed(this.saveButton);
    await this.statusSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.setAddressInput('address');
    await waitUntilDisplayed(this.saveButton);
    await this.setEmailInput('email');
    await waitUntilDisplayed(this.saveButton);
    await this.setDateRegisteredInput('01-01-2001');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
