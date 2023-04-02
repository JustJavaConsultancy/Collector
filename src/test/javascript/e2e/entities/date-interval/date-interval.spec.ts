import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import DateIntervalComponentsPage from './date-interval.page-object';
import DateIntervalUpdatePage from './date-interval-update.page-object';
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

describe('DateInterval e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dateIntervalComponentsPage: DateIntervalComponentsPage;
  let dateIntervalUpdatePage: DateIntervalUpdatePage;
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
    dateIntervalComponentsPage = new DateIntervalComponentsPage();
    dateIntervalComponentsPage = await dateIntervalComponentsPage.goToPage(navBarPage);
  });

  it('should load DateIntervals', async () => {
    expect(await dateIntervalComponentsPage.title.getText()).to.match(/Date Intervals/);
    expect(await dateIntervalComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete DateIntervals', async () => {
    const beforeRecordsCount = (await isVisible(dateIntervalComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(dateIntervalComponentsPage.table);
    dateIntervalUpdatePage = await dateIntervalComponentsPage.goToCreateDateInterval();
    await dateIntervalUpdatePage.enterData();
    expect(await isVisible(dateIntervalUpdatePage.saveButton)).to.be.false;

    expect(await dateIntervalComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(dateIntervalComponentsPage.table);
    await waitUntilCount(dateIntervalComponentsPage.records, beforeRecordsCount + 1);
    expect(await dateIntervalComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await dateIntervalComponentsPage.deleteDateInterval();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(dateIntervalComponentsPage.records, beforeRecordsCount);
      expect(await dateIntervalComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(dateIntervalComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
