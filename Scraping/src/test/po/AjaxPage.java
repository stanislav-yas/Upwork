package po;

import org.openqa.selenium.WebDriver;

public class AjaxPage extends AjaxPageObject {

  private String url;

  public AjaxPage(WebDriver driver, int timeout, String url){
    super(driver, timeout);
    this.url = url;
    open();
  }

  public void open(){
    driver.navigate().to(url);
  }
}
