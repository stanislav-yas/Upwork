package scrape_1_www_iecaonline_com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import po.AjaxPage;

public class DetailPage extends AjaxPage {

  public DetailPage(WebDriver driver, int timeout, String url) {
    super(driver, timeout, url);
    wait.until(ExpectedConditions.textToBe(By.cssSelector("#primary-header > div.container > h1"), "PROFILE DETAILS"));
  }

  public String getEmail(){
    String email = "";
    try {
      WebElement emailEl = driver.findElement(By.cssSelector("div.uprofile_dis_wrap label:nth-child(6) a"));
      new Actions(driver).moveToElement(emailEl).perform();
      email = emailEl.getText();
    }catch (Exception ex){}
    return  email;
  }
}
