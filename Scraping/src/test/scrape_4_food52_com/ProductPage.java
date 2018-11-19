package scrape_4_food52_com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import po.AjaxPage;

import java.util.List;

public class ProductPage extends AjaxPage {

  @FindBy(css = ".product-name")
  protected WebElement title;

  @FindBy(css = ".merchant-name")
  protected WebElement brand;

  @FindBy(css = ".product-price")
  protected WebElement price;

  @FindBy(css = ".product-chooser__variant")
  protected WebElement variantChooser;

  @FindBy(css = ".product-description > p")
  protected WebElement description;

  @FindBy(css = "li.product-images__list-item picture img")
  protected List<WebElement> images; //[data-zoom-src]

  public ProductPage(WebDriver driver, int timeout, String url){
    super(driver, timeout, url);
  }

}
