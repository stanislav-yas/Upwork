package po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AjaxPageObject {

  public WebDriver driver;
  public WebDriverWait wait;
  public int timeout;

  public AjaxPageObject(WebDriver driver, int timeout){
    this.driver = driver;
    this.wait = new WebDriverWait(driver, timeout);
    this.timeout = timeout;
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeout), this);
  }

}
