package scrape_2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import po.AjaxPage;

import java.util.List;

public class MySearchPage2 extends AjaxPage {

  @FindBy(how = How.CSS, using = "#directoryTable > tbody > tr[style='display: table-row;'")
  protected List<WebElement> resultRows;

  @FindBy(css="div#entryWindow.modal_window")
  protected WebElement modal_window;

  protected WebElement cityField;

  protected WebElement stateField;

  protected WebElement countryField;
  protected Select countrySelect /*= new Select(countryField)*/;

  protected WebElement searchBtn;

  public MySearchPage2(WebDriver driver, int timeout, String url){
    super(driver, timeout, url);
    countrySelect = new Select(countryField);
  }

  public void selectCountry(String country){
    countrySelect.selectByValue(country);
  }

  public void search(){
    clickElementJS(searchBtn);
    //wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(resultRows.get(0))));
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
    WebElement p4 = modal_window.findElement(By.cssSelector("div#entryText > p:nth-child(4)"));
    String email = "";
    try{
      email = p4.getText().split("\n")[1];
    }catch (Exception e){}
    modal_window.findElement(By.cssSelector("a.close_modal")).click();
    return email;
  }
}
