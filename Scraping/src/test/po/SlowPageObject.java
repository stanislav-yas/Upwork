package po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SlowPageObject {

  public static WebDriver driver;
  public static WebDriverWait wait;
  protected static ElementLocatorFactory locatorFactory;

  public SlowPageObject(){
    PageFactory.initElements(locatorFactory, this);
  }

  public static void init(WebDriver driver, int timeout){
    SlowPageObject.driver = driver;
    SlowPageObject.locatorFactory  = new AjaxElementLocatorFactory(driver, timeout);
    SlowPageObject.wait = new WebDriverWait(driver, timeout);
  }
}
