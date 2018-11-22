package scrape_3_dentistsinuk_co_uk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import po.AjaxPage;

import java.util.List;

public class MySearchPage3 extends AjaxPage {

  @FindBy(how = How.CSS, using = "div.cat-bg-top.wow, div.cat-bg.wow")
  private List<WebElement> resultRows;

  private Actions actions;

  public MySearchPage3(WebDriver driver, int timeout, String url){
    super(driver, timeout, url);
    actions = new Actions(driver);
  }

/*  public int search(){
    return resultRows.size();
    //wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(resultRows.get(0))));
  }*/

  public int getRowCount(){
    return resultRows.size();
  }

  public WebElement getRow(int index){
    return resultRows.get(index);
  }

  public String getDetailUrl(int rowIndex){
    String detailUrl = null;
    try{
      WebElement detailUrlElement = getRow(rowIndex).findElement(By.cssSelector("a.btn.btn-orange"));
      actions.moveToElement(detailUrlElement).perform(); //for visualise
      detailUrl = detailUrlElement.getAttribute("href");
    }catch (Exception ex){
      System.out.println("Error occurred when getting detailUrl:" + ex.getMessage());
    }
    return detailUrl;
  }
}
