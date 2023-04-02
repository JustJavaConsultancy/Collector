import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import FacilityComponentsPage from './facility.page-object';
import FacilityUpdatePage from './facility-update.page-object';
import {
  waitUntilDisplayed,
  waitUntilAnyDisplayed,
  click,
  getRecordsCount,
  waitUntilHidden,
  waitUntilCount,
  isVisible,
} from '../../util/utils';

const expect = chai.expect;

describe('Facility e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let facilityComponentsPage: FacilityComponentsPage;
  let facilityUpdatePage: FacilityUpdatePage;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();
    await signInPage.username.sendKeys(username);
    await signInPage.password.sendKeys(password);
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();
    await waitUntilDisplayed(navBarPage.entityMenu);
    await waitUntilDisplayed(navBarPage.adminMenu);
    await waitUntilDisplayed(navBarPage.accountMenu);
  });

  beforeEach(async () => {
    await browser.get('/');
    await waitUntilDisplayed(navBarPage.entityMenu);
    facilityComponentsPage = new FacilityComponentsPage();
    facilityComponentsPage = await facilityComponentsPage.goToPage(navBarPage);
  });

  it('should load Facilities', async () => {
    expect(await facilityComponentsPage.title.getText()).to.match(/Facilities/);
    expect(await facilityComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Facilities', async () => {
    const beforeRecordsCount = (await isVisible(facilityComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(facilityComponentsPage.table);
    facilityUpdatePage = await facilityComponentsPage.goToCreateFacility();
    await facilityUpdatePage.enterData();
    expect(await isVisible(facilityUpdatePage.saveButton)).to.be.false;

    expect(await facilityComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(facilityComponentsPage.table);
    await waitUntilCount(facilityComponentsPage.records, beforeRecordsCount + 1);
    expect(await facilityComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await facilityComponentsPage.deleteFacility();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(facilityComponentsPage.records, beforeRecordsCount);
      expect(await facilityComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(facilityComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
