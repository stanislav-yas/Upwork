package scrape_2;

import base.TestBase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Scrape2Test extends TestBase {

  @Test
  public void scrape() throws Exception{
    driver = new ChromeDriver();
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter("scrape2.csv"));
      driver.manage().window().setSize(new Dimension(975, 752));
      driver.manage().window().setPosition(new Point(1678,0));
      MySearchPage2 page = new MySearchPage2(driver, 8, "https://integrativemedicine.arizona.edu/alumni.html");
      page.getCell(0,0).click();
      driver.findElement(By.cssSelector("div.modal_window a.close_modal")).click();
    }catch (Exception ex){
      writer.flush(); writer.close();
      throw ex;
    }
  }
}
