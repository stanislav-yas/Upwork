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

public class TestBase {

  protected WebDriver driver, driver2;

  @Before
  public void setUp() throws Exception{
    //csvWrite();
    //startChrome();
    //startChromeViaProxy();
    //startRemoteBrowser(Platform.MAC.toString(),"chrome");
    //startRemoteBrowser(Platform.WINDOWS.toString(),"chrome");
  }

  public void csvWrite(){
    Charset charset = Charset.forName("US-ASCII");
    String s = "teststring";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.csv"))) {
        writer.write(s + ";");
        writer.newLine();
    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
  }

  @After
  public void tearDown() {
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
