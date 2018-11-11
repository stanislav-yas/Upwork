import org.junit.Test;
import org.openqa.selenium.*;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Scrape1Test extends TestBase {

  @Test
  public void scrape1() throws Exception{
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter("result.csv"));
      driver.manage().window().setPosition(new Point(0,0));
      driver.manage().window().setSize(new Dimension(1680, 720));
      MySearchPage page = new MySearchPage(driver,5,"https://www.iecaonline.com/quick-links/member-directory/");
      int pageCnt = 0;
      int rowCnt = 0;
      do {
        pageCnt++;
        for (int i = 0; i < 2 /*page.getRowCount()*/; i++) {
          String firstName = page.getCell(i,1).getText();
          String lastName = page.getCell(i,2).getText();
          String primaryAddress = page.getCell(i,9).getText();
          String url = page.getCell(i,1).findElement(By.cssSelector("a")).getAttribute("href");
          DetailPage dpage = new DetailPage(driver, 5, url);
          String email = dpage.getEmail();
          rowCnt++;
          writer.write((pageCnt) + ";" + (rowCnt) + ";" + firstName + ";" + lastName + ";" + primaryAddress + ";" + email);
          writer.newLine(); writer.flush();
          driver.navigate().back();
        }
      } while (page.nextSearchResultPage() && rowCnt < 15);
    }catch (Exception ex){
      writer.flush(); writer.close();;
      throw ex;
    }
  }

}
