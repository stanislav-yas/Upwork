package po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import po.Page;

import java.util.List;

public class SearchPage extends Page {

  @FindBy(how = How.CSS, using = "#member-grid > tbody > tr")
  private List<WebElement> resultRows;

  public SearchPage(String url){
    super(url);
  }

  public int getRowCount(){
    return resultRows.size();
  }

  public WebElement getRow(int index){
    return resultRows.get(index);
  }
}
