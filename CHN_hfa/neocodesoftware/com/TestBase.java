package neocodesoftware.com;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestBase {

  WebDriver driver;
  WebDriverWait wait;

  @Before
  public void setUp() throws Exception{
    //startChromeViaProxy();
    //startRemoteBrowser(Platform.MAC.toString(),"chrome");
    startRemoteBrowser(Platform.WINDOWS.toString(),"chrome");
    wait = new WebDriverWait(driver, 20);
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
    //System.out.println(((ChromeDriver) driver).getCapabilities());
  }

  private void startRemoteBrowser(String platform, String browserName) throws Exception{
    DesiredCapabilities capability = new DesiredCapabilities();
    capability.setCapability("platform", platform);
    capability.setCapability("browser", browserName);
    //capability.setCapability("browserVersion", browserVersion);
    capability.setCapability("build", "CHN_hfa:"+platform);
    driver = new RemoteWebDriver(
            new URL("https://stanislav158:LYwTXaH3Tpm2tzzJNnzM@hub-cloud.browserstack.com/wd/hub"),
            capability
    );
    ((RemoteWebDriver)driver).setFileDetector(new LocalFileDetector());
  }

  protected File takeScreenshot(){
    //driver = new Augmenter().augment(driver);
    File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
/*    try {
      FileUtils.copyFile(srcFile, new File("Screenshot.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }*/
    return file;
  }

}
