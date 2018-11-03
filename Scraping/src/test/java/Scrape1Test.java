import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.List;

public class Scrape1Test extends TestBase {

  @FindBy(css="#member-grid > tbody > tr")
  List<WebElement> rows;

  @Test
  public void scrape1(){
    driver.manage().window().maximize();
    driver.get("https://www.iecaonline.com/quick-links/member-directory/");
    int rowCount = rows.size(); //driver.findElements(By.cssSelector("#member-grid > tbody > tr")).size();
    for (int i = 0; i < rowCount; i++) {
      List<WebElement> tds = driver.findElements(By.cssSelector("#member-grid > tbody > tr:nth-child(" + (i + 1)+ ") > td"));
      String firstName = tds.get(1).getText();
      String lastName = tds.get(2).getText();
      String primaryAddress = tds.get(9).getText();
      tds.get(1).findElement(By.cssSelector("a")).click();
      wait.until(ExpectedConditions.textToBePresentInElementLocated((By.cssSelector("#primary-header > div.container > h1")), "Profile Details"));
      String email = driver.findElement(By.cssSelector("div.uprofile_dis_wrap label:nth-child(6) a")).getText();
      driver.navigate().back();
    }
  }
}
