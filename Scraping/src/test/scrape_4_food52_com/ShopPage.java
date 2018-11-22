package scrape_4_food52_com;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import po.AjaxPage;
import util.TestBase;
import java.util.List;

class ShopPage extends AjaxPage {

  @FindBy(css = "ul.shop__nav-links--secondary li.shop__nav-subheader")
  List<WebElement> topMenu;

  @FindBy(css = ".shop__sidebar ul li a")
  private List<WebElement> sideMenu;

  @FindBy(css = "div.shop__grid div.shop__item a")
  private List<WebElement> shopItems;

/*  @FindBy(css = "div.pagination a")
  private List<WebElement> pagination;

  @FindBy(css = "div.pagination a.next_page")
  private WebElement nextButton;*/

  private Actions actions;

  ShopPage(WebDriver driver, int timeout, String url){
    super(driver, timeout, url);
    actions = new Actions(driver);
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

  void scrapeShop(){
    //int grandTotalItems = 0;
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
        //actions.moveToElement(menuItem).perform(); doesn't scroll fully
        scrollIntoView(menuItem); // scroll until view
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
            WebElement shopItem = shopItems.get(j);
            actions.moveToElement(shopItem).perform(); // for visualisation
            String productLink = shopItem.getAttribute("href");
            ProductPage productPage = new ProductPage(TestBase.driver2, 8, productLink);
            //"Num; MainCategory; SubCategory; Brand; Product Link; Title; Price; Product Description; Image1; Image2; Image3"
            lineNum++;
            Scrape4Test.writer.addValue(Integer.toString(lineNum))
                .addValue(mainCategory)
                .addValue(subCategory)
                .addValue(productPage.getBrand())
                .addValue(productLink)
                .addValue(productPage.getTitle())
                .addValue(productPage.getPrice());
            List<WebElement> images = productPage.getImages();
            int iSize = images.size();
            if(iSize > 3) iSize = 3;
            for (int k = 0; k < iSize; k++) {
              try {
                Scrape4Test.writer.addValue(images.get(k).getAttribute("data-zoom-src"));
              }catch (Exception e){Scrape4Test.writer.addValue("");}
            }
            Scrape4Test.writer.nextLine();
          }
        }while(hasNextPage());
      }
    }
    System.out.println("Total items: " + lineNum);
  }

}
