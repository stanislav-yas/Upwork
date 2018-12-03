package util;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class ProxyList{

  private ArrayList list;
  private Random rnd = new Random();
  private int previousRandomIndex = -1;

  public ProxyList(WebDriver driver){
    init(driver, -1);
  }

  public ProxyList(WebDriver driver, int num){
    init(driver, num);
  }

  private void init(WebDriver driver, int num){
    driver.navigate().to("https://www.free-proxy-list.net/");
    driver.findElement(By.cssSelector("select[name='proxylisttable_length'] > option:last-child")).click();
    driver.findElement(By.cssSelector("#proxylisttable > tfoot > tr > th:nth-child(5) > select option[value='elite proxy']")).click();
    List<WebElement> rows = driver.findElements(By.cssSelector("table#proxylisttable tbody tr"));
    int listSize = rows.size();
    if(num > 0 && num < listSize){
      listSize = num;
    }
    list = new ArrayList(listSize);
    for (int i=0; i < listSize; i++ ) {
      String ip = rows.get(i).findElement(By.cssSelector("td:nth-child(1)")).getText();
      String port = rows.get(i).findElement(By.cssSelector("td:nth-child(2)")).getText();
      String code = rows.get(i).findElement(By.cssSelector("td:nth-child(3)")).getText();
      list.add(new Entry(ip, port, code));
    }
  }

  public int getSize(){
    return list.size();
  }

  public Entry getProxyByNum(int num){
    return (Entry)list.get(num);
  }

  public Entry getRandomProxy(){
    int nextIndex;
    do{
       nextIndex = rnd.nextInt(list.size());
    }
    while(nextIndex == previousRandomIndex);
    previousRandomIndex = nextIndex;
    return (Entry) list.get(nextIndex);
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

    public String getIp() {
      return ip;
    }

    public String getPort() {
      return port;
    }

    public String getCode() {
      return code;
    }

    public String getProxy(){
      return String.format("%s:%s",ip,port);
    }

    @Override
    public String toString() {
      return String.format("%s:%s[%s]",ip,port,code);
    }
  }

}
