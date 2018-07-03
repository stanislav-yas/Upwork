package neocodesoftware.com;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

  WebDriver driver;
  WebDriverWait wait;

  @Before
  public void setUp() {
    startChromeViaProxy();
  }


  @After
  public void tearDown() {
    driver.quit();
  }

  private void startChrome(){
    driver = new ChromeDriver();
  }

  private void startChromeViaProxy(){
    String proxyStr = "67.205.148.246:8080";//"198.50.137.181";
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--proxy-server=" + proxyStr);
    driver = new ChromeDriver(chromeOptions);
    System.out.println(((ChromeDriver) driver).getCapabilities());
  }

}
