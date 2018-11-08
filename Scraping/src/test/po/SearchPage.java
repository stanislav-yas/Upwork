package po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class SearchPage extends Page {

  @FindBy(how = How.CSS, using = "#member-grid > tbody > tr")
  private List<WebElement> resultRows;

  @FindBy(css = "a#member-grid_previous")
  private WebElement buttonPrevious;

  @FindBy(css = "a#member-grid_next")
  private WebElement buttonNext;

  public SearchPage(String url){
    super(url);
  }

  public boolean isButtonNextEnabled(){
    if(buttonNext == null){
      return false;
    }
    if(buttonNext.getAttribute("class").contains("disabled")){
      return false;
    }else{
      return true;
    }
  }

  public boolean nextSearchResultPage(){
    boolean res = isButtonNextEnabled();
    buttonNext.click();
    return res;
  }

  public int getRowCount(){
    return resultRows.size();
  }

  public WebElement getRow(int index){
    return resultRows.get(index);
  }
}
