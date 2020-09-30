package ru.stqa.katja.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.katja.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

import java.io.IOException;

public class TestBase {

  protected static ApplicationManager app;

  static {
    try {
      app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @BeforeSuite()
  public void setUp() throws IOException {
    app.init();
  }

  @AfterSuite()
  public void tearDown() {
    app.stop();
  }

}
