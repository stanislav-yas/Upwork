package util;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class ProxyList{

  private WebDriver driver;
  private ArrayList list;
  private Random rnd = new Random();

  public ProxyList(WebDriver driver){
    this.driver = driver;
    driver.navigate().to("https://www.free-proxy-list.net/");
    driver.findElement(By.cssSelector("select[name='proxylisttable_length'] > option:last-child")).click();
    driver.findElement(By.cssSelector("#proxylisttable > tfoot > tr > th:nth-child(5) > select option[value='elite proxy']")).click();
    List<WebElement> rows = driver.findElements(By.cssSelector("table#proxylisttable tbody tr"));
    list = new ArrayList(rows.size());
    for (WebElement row:rows) {
      String ip = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
      String port = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String code = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
     list.add(new Entry(ip,port,code));
    }
  }

  public Entry getProxyByNum(int num){
    return (Entry)list.get(num);
  }

  public class Entry{
    private String ip;
    private String port;
    private String code;

    private Entry(String ip, String port, String code){
      this.ip = ip;
      this.port = port;
      this.code = code;
    }
  }

}
