package scrape_4_food52_com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import po.AjaxPage;

import java.util.List;

class ProductPage extends AjaxPage {

  @FindBy(css = ".product-name")
  private WebElement title;

  @FindBy(css = ".merchant-name")
  private WebElement brand;

  @FindBy(css = ".product-price")
  private WebElement price;

/*  @FindBy(css = ".product-chooser__variant")
  private WebElement variantChooser;

  @FindBy(css = ".product-description > p")
  private WebElement description;*/

  @FindBy(css = "li.product-images__list-item picture img")
  private List<WebElement> images;

  private Actions actions;

  ProductPage(WebDriver driver, int timeout, String url){
    super(driver, timeout, url);
    actions = new Actions(driver);
  }

  String getTitle(){
    try{
      return title.getText();
    }catch (Exception e){ return "";}
  }

  String getBrand(){
    try{
      return brand.getText();
    }catch (Exception e){ return "";}
  }

  String getPrice(){
    try{
      //actions.moveToElement(price).perform(); // for visualisation
      return price.getText();
    }catch (Exception e){ return "";}
  }

/*  public String getDescription(){
    try{
      return description.getText();
    }catch (Exception e){ return "";}
  }*/

  List<WebElement> getImages(){
    return images;
  }

}
