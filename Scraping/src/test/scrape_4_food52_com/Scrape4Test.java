package scrape_4_food52_com;
/*
 Looking for someone to scrape various retail websites for all product specific data
 (retailer, brand, product link, name, price, description, images).
 For example, if given the website food52.com,
 we would want a Google Sheets document listing all of the above information
 for each of the products they sell through their site.
*/

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import util.CsvWriter;
import util.TestBase;

public class Scrape4Test extends TestBase {

  protected static CsvWriter writer;
  private ShopPage shopPage;

  @Test
  public void scrape() throws Exception{
    driver = new ChromeDriver(/*new ChromeOptions().setHeadless(true)*/);
    driver2 = new ChromeDriver(new ChromeOptions().setHeadless(true));
    try {
      driver.manage().window().setSize(new Dimension(1920, 746));
      driver.manage().window().setPosition(new Point(1921,0));
      driver2.manage().window().setSize(new Dimension(960, 746));
      driver2.manage().window().setPosition(new Point(1921+960,0));
/*      driver.manage().window().setSize(new Dimension(975, 530));
      driver.manage().window().setPosition(new Point(1678,0));
      driver2.manage().window().setSize(new Dimension(975, 530));
      driver2.manage().window().setPosition(new Point(1678,530));*/
      driver.manage().window().maximize();
      driver2.manage().window().maximize();
      writer = new CsvWriter("results\\scrape4.csv");
      writer.addValue("Num; MainCategory; SubCategory; Brand; Product Link; Title; Price; Product Description; Image1; Image2; Image3"); writer.nextLine();
      shopPage = new ShopPage(driver, 8, "https://food52.com/shop/");
      while(shopPage.topMenu.size() == 0){
        shopPage.driver.navigate().refresh();
      }
      shopPage.scrapeShop();
    }catch (Exception ex){
      throw ex;
    }finally {
      writer.flush(); writer.close();
    }
  }

  private void processResultTable(){

  }
}
