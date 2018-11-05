package po;

public class Page extends PageObject {

  private String url;

  public Page(String url){
    super();
    this.url = url;
  }

  public void open(){
    driver.navigate().to(url);
  }
}
