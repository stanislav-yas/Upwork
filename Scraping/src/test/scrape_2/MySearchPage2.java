package scrape_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import po.AjaxPage;

import java.util.List;

public class MySearchPage2 extends AjaxPage {

  @FindBy(how = How.CSS, using = "#directoryTable > tbody > tr")
  protected List<WebElement> resultRows;

  @FindBy(css="div#entryWindow.modal_window")
  protected WebElement modal_window;

  //a.close_modal

  public MySearchPage2(WebDriver driver, int timeout, String url){
    super(driver, timeout, url);
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

  public String getName(int rowIndex){
    return getCell(rowIndex, 0).getText();
  }

  public String getProfession(int rowIndex){
    return getCell(rowIndex, 1).getText();
  }

  public String getEmail(int rowIndex){
    getCell(rowIndex, 0).click();
    wait.until(ExpectedConditions.visibilityOf(modal_window));
    WebElement email = modal_window.findElement(By.id("div#entryText > p:nth-child(4) br"));
    return "";
  }
}
