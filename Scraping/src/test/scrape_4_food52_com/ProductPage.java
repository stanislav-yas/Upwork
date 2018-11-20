package scrape_4_food52_com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import po.AjaxPage;

import java.util.List;

public class ProductPage extends AjaxPage {

  @FindBy(css = ".product-name")
  private WebElement title;

  @FindBy(css = ".merchant-name")
  private WebElement brand;

  @FindBy(css = ".product-price")
  private WebElement price;

  @FindBy(css = ".product-chooser__variant")
  private WebElement variantChooser;

  @FindBy(css = ".product-description > p")
  private WebElement description;

  @FindBy(css = "li.product-images__list-item picture img")
  private List<WebElement> images; //[data-zoom-src]

  public ProductPage(WebDriver driver, int timeout, String url){
    super(driver, timeout, url);
  }

  public String getTitle(){
    try{
      return title.getText();
    }catch (Exception e){ return "";}
  }

  public String getBrand(){
    try{
      return brand.getText();
    }catch (Exception e){ return "";}
  }

  public String getPrice(){
    try{
      return price.getText();
    }catch (Exception e){ return "";}
  }

  public String getDescription(){
    try{
      return description.getText();
    }catch (Exception e){ return "";}
  }

  public List<WebElement> getImages(){
    return images;
  }

}
