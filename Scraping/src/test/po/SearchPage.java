package po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends AjaxPage {

  public List<WebElement> resultRows;

  protected WebElement buttonPrevious;

  protected WebElement buttonNext;

  public SearchPage(WebDriver driver, int timeout, String url){
    super(driver, timeout, url);
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
    wait.until(ExpectedConditions.stalenessOf(resultRows.get(0)));
    wait.until(ExpectedConditions.visibilityOf(resultRows.get(0)));
    //getRowCount();
    return res;
  }

  public int getRowCount(){
    return resultRows.size();
  }

  public WebElement getRow(int index){
    return resultRows.get(index);
  }

}
