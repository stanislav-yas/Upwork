package scrape_1;

import util.TestBase;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Scrape1Test extends TestBase {

  @Test
  public void scrape1() throws Exception{
    driver = new ChromeDriver();
    driver2 = new ChromeDriver();
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter("result.csv"));
      driver.manage().window().setSize(new Dimension(975, 752));
      driver.manage().window().setPosition(new Point(1678,0));
      driver2.manage().window().setSize(new Dimension(975, 793));
      driver2.manage().window().setPosition(new Point(1678,247));
      MySearchPage page = new MySearchPage(driver,10,"https://www.iecaonline.com/quick-links/member-directory/");
      int pageCnt = 0;
      int rowCnt = 0;
      writer.write("Page;Row;First_Name;Last_Name;Primary_Address;Email") ;writer.newLine();
      do {
        pageCnt++;
        for (int i = 0; i < page.getRowCount(); i++) {
          String firstName = page.getCell(i,1).getText();
          String lastName = page.getCell(i,2).getText();
          String primaryAddress = page.getCell(i,9).getText();
          String url = page.getCell(i,1).findElement(By.cssSelector("a")).getAttribute("href");
          String email = "";
          try {
            DetailPage dpage = new DetailPage(driver2, 5, url);
            email = dpage.getEmail();
          }catch (Exception ex){
            System.out.println("Error:"+ex.getMessage());
          }
          rowCnt++;
          writer.write((pageCnt) + ";" + (rowCnt) + ";" + firstName + ";" + lastName + ";" + primaryAddress + ";" + email);
          writer.newLine(); writer.flush();
        }
      } while (page.nextSearchResultPage());
    }catch (Exception ex){
      writer.flush(); writer.close();
      throw ex;
    }
  }
}
