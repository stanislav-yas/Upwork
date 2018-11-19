package scrape_4_food52_com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import po.AjaxPage;
import util.TestBase;
import java.util.List;

public class ShopPage extends AjaxPage {

  @FindBy(css = "ul.shop__nav-links--secondary li.shop__nav-subheader")
  public List<WebElement> topMenu;

  @FindBy(css = ".shop__sidebar ul li a")
  private List<WebElement> sideMenu;

  @FindBy(css = "div.shop__grid div.shop__item a")
  private List<WebElement> shopItems;

/*  @FindBy(css = "div.pagination a")
  private List<WebElement> pagination;

  @FindBy(css = "div.pagination a.next_page")
  private WebElement nextButton;*/

  public ShopPage(WebDriver driver, int timeout, String url){
    super(driver, timeout, url);
  }

  public List<WebElement> getSecondLevelMenu(int index){
    List<WebElement> secondLevelMenu = null;
    try{
      secondLevelMenu = topMenu.get(index).findElements(By.cssSelector("li > a"));
    }catch (Exception e){}
    return secondLevelMenu;
  }

  private boolean hasNextPage(){
    List<WebElement> pagination = driver.findElements(By.cssSelector("div.pagination a"));
    int psize = pagination.size();
    if(psize > 0){
      WebElement lastButton = pagination.get(psize-1);
      if(lastButton.getAttribute("class").equals("next_page")){
        lastButton.click();
        return true;
      }
    }
    return false;
  }

  public void scrapeShop(){
    int grandTotalItems = 0;
    int lineNum = 0;
    String mainCategory, subCategory;
    for (int i = 0; i < topMenu.size(); i++) {
      WebElement item = topMenu.get(i);
      mainCategory = item.getText();
      System.out.println(mainCategory);
      item.click(); //select main category
      //System.out.println("sideMenu size=" + sideMenu.size());
      for (int y = 0; y < sideMenu.size(); y++){
        WebElement menuItem = sideMenu.get(y);
        subCategory = menuItem.getText();
        System.out.println("  " + subCategory);
        menuItem.click(); //select subcategory
/*      int itemsPerPage = shopItems.size();
        int pageCount = 1;
        int paginationSize = pagination.size();
        if( paginationSize > 0){
          pageCount = Integer.parseInt(pagination.get(paginationSize-2).getText());
        }
        int totalItems = pageCount * itemsPerPage;
        grandTotalItems += totalItems;
        System.out.println(" - " + itemsPerPage + " x " + pageCount + " = " + totalItems + " items");*/
        do {
          for (int j = 0; j < shopItems.size(); j++) {
            String productLink = shopItems.get(j).getAttribute("href");
            ProductPage productPage = new ProductPage(TestBase.driver2, 8, productLink);
            //"Num; MainCategory; SubCategory; Brand; Product Link; Title; Price; Product Description; Image1; Image2; Image3"
            lineNum++;
            Scrape4Test.writer.addValue(Integer.toString(lineNum))
                .addValue(mainCategory)
                .addValue(subCategory)
                .addValue(productPage.brand.getText())
                .addValue(productLink)
                .addValue(productPage.title.getText())
                .addValue(productPage.price.getText());
            for (int k = 0; k < productPage.images.size(); k++) {
              Scrape4Test.writer.addValue(productPage.images.get(k).getAttribute("data-zoom-src"));
            }
            Scrape4Test.writer.nextLine();
          }
        }while(hasNextPage());
      }
    }
    System.out.println("Total items: " + grandTotalItems);
  }

}
