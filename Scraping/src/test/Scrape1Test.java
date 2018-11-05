import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import po.SearchPage;

import java.util.Iterator;
import java.util.List;

public class Scrape1Test extends TestBase {

  @Test
  public void scrape1(){
    driver.manage().window().maximize();
    SearchPage page = new SearchPage("https://www.iecaonline.com/quick-links/member-directory/");
    page.open();
    int rowCount = page.getRowCount();
    for (int i = 0; i < rowCount; i++) {
      WebElement row = page.getRow(i); //"#member-grid > tbody > tr:nth-child(" + (i + 1)+ ") > td"
      String firstName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String lastName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String primaryAddress = row.findElement(By.cssSelector("td:nth-child(10)")).getText();
      row.findElement(By.cssSelector("td:nth-child(2)")).findElement(By.cssSelector("a")).click();
      wait.until(ExpectedConditions.textToBePresentInElementLocated((By.cssSelector("#primary-header > div.container > h1")), "Profile Details"));
      String email = driver.findElement(By.cssSelector("div.uprofile_dis_wrap label:nth-child(6) a")).getText();
      driver.navigate().back();
    }
  }
}
