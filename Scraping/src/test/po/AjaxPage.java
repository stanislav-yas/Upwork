package po;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AjaxPage /*extends AjaxPageObject*/ {

  public WebDriver driver;
  public WebDriverWait wait;
  private String url;

  public AjaxPage(WebDriver driver, int timeout, String url){
    //super(driver, timeout);
    this.url = url;
    this.driver = driver;
    this.wait = new WebDriverWait(driver, timeout);
    open();
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeout), this);
  }

  public void open(){
    driver.navigate().to(url);
  }

  protected void clickElementJS(WebElement element){
    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
  }
}
