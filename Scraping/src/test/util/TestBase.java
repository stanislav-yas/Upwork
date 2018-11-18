package util;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.Instant;

public class TestBase {

  public static WebDriver driver, driver2;
  private Instant beginTime = Instant.now();

  @Before
  public void setUp() throws Exception{
    //startChrome();
    //startChromeViaProxy();
    //startRemoteBrowser(Platform.MAC.toString(),"chrome");
    //startRemoteBrowser(Platform.WINDOWS.toString(),"chrome");
  }

  @After
  public void tearDown() {
    Duration timeElapsed = Duration.between(beginTime, Instant.now());
    System.out.println("Time elapsed: "+ timeElapsed);
    if(driver != null){
      driver.quit();
    }
    if(driver2 != null){
      driver2.quit();
    }
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
