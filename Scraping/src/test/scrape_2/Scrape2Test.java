package scrape_2;

import util.CsvWriter;
import util.TestBase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Scrape2Test extends TestBase {

  private CsvWriter writer;
  private MySearchPage2 page;

  @Test
  public void scrape() throws Exception{
    driver = new ChromeDriver();
    try {
      writer = new CsvWriter("scrape2.csv");
      //driver.manage().window().setSize(new Dimension(975, 752));
      driver.manage().window().setPosition(new Point(1920/*1678*/,0));
      driver.manage().window().maximize();
      page = new MySearchPage2(driver, 8, "https://integrativemedicine.arizona.edu/alumni.html");
      System.out.println("Number of countries: " + page.countrySelect.getOptions().size());
      System.out.println("Number of countries: " + page.countrySelect.getOptions().size());
      page.selectCountry("Brazil");
      page.search();
      processResultTable();
      page.selectCountry("Israel");
      page.search();
      processResultTable();
    }catch (Exception ex){
      writer.flush(); writer.close();
      throw ex;
    }
  }

  private void processResultTable(){
    for (int i = 0; i < page.getRowCount(); i++) {
      writer.addValue(page.getName(i))
          .addValue(page.getProfession(i))
          .addValue(page.getEmail(i))
          .nextLine();
    }
  }
}
