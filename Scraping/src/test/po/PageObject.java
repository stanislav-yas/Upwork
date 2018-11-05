package po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

  protected static WebDriver driver;
  protected static WebDriverWait wait;

  public PageObject(){
    PageFactory.initElements(driver, this);
  }

  public static void init(WebDriver driver, WebDriverWait wait){
    PageObject.driver = driver;
    PageObject.wait = wait;
  }
}
