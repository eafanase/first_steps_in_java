package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.katja.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  @BeforeSuite()
  public void setUp() {
    app.init();
  }

  @AfterSuite()
  public void tearDown() {
    app.stop();
  }

}
