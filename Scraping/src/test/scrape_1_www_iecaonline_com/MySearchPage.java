package scrape_1_www_iecaonline_com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import po.AjaxPage;

import java.util.List;

public class MySearchPage extends AjaxPage {

  @FindBy(how = How.CSS, using = "#member-grid > tbody > tr")
  protected List<WebElement> resultRows;

  @FindBy(css = "a#member-grid_previous")
  protected WebElement buttonPrevious;

  @FindBy(css = "a#member-grid_next")
  protected WebElement buttonNext;

  public MySearchPage(WebDriver driver, int timeout, String url){
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
    boolean enabled = isButtonNextEnabled();
    if (enabled) {
      buttonNext.click();
      wait.until(ExpectedConditions.stalenessOf(resultRows.get(0)));
      wait.until(ExpectedConditions.visibilityOf(resultRows.get(0)));
    }
    return enabled;
  }

  public int getRowCount(){
    return resultRows.size();
  }

  public WebElement getRow(int index){
    return resultRows.get(index);
  }

  public WebElement getCell(int rowIndex, int columnIndex){
    return getRow(rowIndex).findElement(By.cssSelector("td:nth-child("+(columnIndex+1)+")"));
  }

}
