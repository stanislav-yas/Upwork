package scrape_3_dentistsinuk.co.uk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import po.AjaxPage;

public class DetailPage extends AjaxPage {

  @FindBy(css = ".detail-contact-01 > div[itemprop='address'] > h3[itemprop='name']")
  WebElement nameElement;

  @FindBy(css = ".detail-contact-01 span[itemprop='email']")
  WebElement emailElement;

  @FindBy(css = ".detail-contact-01 a[itemprop='url']")
  WebElement webUrlElement;

  public DetailPage(WebDriver driver, int timeout, String url) {
    super(driver, timeout, url);
    //wait.until(ExpectedConditions.textToBe(By.cssSelector("#primary-header > div.container > h1"), "PROFILE DETAILS"));
  }

  public String getName(){
    String name = "";
    try {
      name = nameElement.getText();
    }catch (Exception ex){
      System.out.println("Error occurred in getting Name: " + ex.getMessage());
    }
    return  name;
  }

  public String getEmail(){
    String email = "";
    try {
      email = emailElement.getText();
    }catch (Exception ex){
      System.out.println("Error occurred in getting Email: " + ex.getMessage());
    }
    return  email;
  }

  public String getWebUrl(){
    String email = "";
    try {
      email = webUrlElement.getText();
    }catch (Exception ex){
      System.out.println("Error occured in getting WebUrl: " + ex.getMessage());
    }
    return  email;
  }
}
