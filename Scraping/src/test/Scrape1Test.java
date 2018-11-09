import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import po.SearchPage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Scrape1Test extends TestBase {

  @Test
  public void scrape1() throws Exception{
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter("result.csv"));
      driver.manage().window().maximize();
      SearchPage page = new SearchPage("https://www.iecaonline.com/quick-links/member-directory/");
      page.open();
      int rowCount = page.getRowCount();
      int totalRows = 0;
      do {
        for (int i = 0; i < rowCount; i++) {
          WebElement row = page.getRow(i); //"#member-grid > tbody > tr:nth-child(" + (i + 1)+ ") > td"
          String firstName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
          String lastName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
          String primaryAddress = row.findElement(By.cssSelector("td:nth-child(10)")).getText();
          row.findElement(By.cssSelector("td:nth-child(2)")).findElement(By.cssSelector("a")).click();
          page.wait.until(ExpectedConditions.textToBe(By.cssSelector("#primary-header > div.container > h1"), "PROFILE DETAILS"));
          String email = driver.findElement(By.cssSelector("div.uprofile_dis_wrap label:nth-child(6) a")).getText();
          totalRows++;
          writer.write((i + 1) + ";" + firstName + ";" + lastName + ";" + primaryAddress + ";" + email);
          writer.newLine();
          driver.navigate().back();
        }
      } while (page.nextSearchResultPage() && totalRows < 15);
    }catch (Exception ex){
      writer.flush(); writer.close();;
      throw ex;
    }
  }

}
