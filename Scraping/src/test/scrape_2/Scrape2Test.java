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
      driver.manage().window().setSize(new Dimension(975, 1039));
      driver.manage().window().setPosition(new Point(1678,0));
      //driver.manage().window().maximize();
      writer = new CsvWriter("scrape2.csv");
      writer.addValue("Name; Speciality; Clinic; City; Country; Email"); writer.nextLine();
      page = new MySearchPage2(driver, 8, "https://integrativemedicine.arizona.edu/alumni.html");
      page.selectCountry("Japan");
      System.out.println("rows found: " + page.search());
      processResultTable();
      page.selectCountry("Israel");
      System.out.println("rows found: " + page.search());
      processResultTable();
      page.selectCountry("Canada");
      System.out.println("rows found: " + page.search());
      processResultTable();
      page.selectCountry("US");
      page.selectState("FL");
      System.out.println("rows found: " + page.search());
      processResultTable();
      page.selectState("IL");
      System.out.println("rows found: " + page.search());
      processResultTable();
    }catch (Exception ex){
      throw ex;
    }finally {
      writer.flush(); writer.close();
    }
  }

  private void processResultTable(){
    for (int i = 0; i < page.getRowCount(); i++) {
      writer.addValue(page.getName(i))
          .addValue(page.getSpeciality(i))
          .addValue(page.getClinic(i))
          .addValue(page.getCity(i))
          .addValue(page.getCountry(i))
          .addValue(page.getEmail(i))
          .nextLine();
    }
  }
}
