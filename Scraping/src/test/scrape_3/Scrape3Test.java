package scrape_3;
/*I’m looking for someone to help me build a list of contacts of dentists in the UK.
Here is a website that lists out a lot of them. I've split the URLs into sections of the UK.
http://www.dentistsinuk.co.uk/england
http://www.dentistsinuk.co.uk/wales
http://www.dentistsinuk.co.uk/scotland
http://www.dentistsinuk.co.uk/northern-ireland
I need you to gather their:
• Dental Practice Name
• Email Address (ESSENTIAL)
• Website URL
I’ll just need you to populate it into a Google Sheet.
Fixed-price project - $100*/

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import util.CsvWriter;
import util.TestBase;

public class Scrape3Test extends TestBase {

  private CsvWriter writer;
  private MySearchPage3 page;

  @Test
  public void scrape() throws Exception{
    driver = new ChromeDriver();
    driver2 = new ChromeDriver();
    try {
      driver.manage().window().setSize(new Dimension(975, 530));
      driver.manage().window().setPosition(new Point(1678,0));
      driver2.manage().window().setSize(new Dimension(975, 530));
      driver2.manage().window().setPosition(new Point(1678,530));
      //driver.manage().window().maximize();
      writer = new CsvWriter("results\\scrape3.csv");
      writer.addValue("Num; Dental Practice Name; Email; Website"); writer.nextLine();
      page = new MySearchPage3(driver, 8, "http://www.dentistsinuk.co.uk/northern-ireland");
      processResultTable();
    }catch (Exception ex){
      throw ex;
    }finally {
      writer.flush(); writer.close();
    }
  }

  private void processResultTable(){
    System.out.println("searched rows: " + page.getRowCount());
    for (int i = 0; i < page.getRowCount(); i++) {
      String detailUrl = page.getDetailUrl(i);
      if(detailUrl != null){
        DetailPage dpage = new DetailPage(driver2, 9, detailUrl);
        writer.addValue(String.valueOf(i+1))
            .addValue(dpage.getName())
            .addValue(dpage.getEmail())
            .addValue(dpage.getWebUrl())
            .nextLine();
      }

    }
  }
}